

import { computeFrictionFactor } from './friction.js';



const ctx = document.getElementById('chart').getContext('2d');

const projectionLinesPlugin = {
    id: 'projectionLines',
    afterDatasetsDraw(chart) {

        const { ctx, scales } = chart;
        const dataset = chart.data.datasets[1]; // Current Point

        if (!dataset.data.length) return;

        const point = dataset.data[0];

        const xPixel = scales.x.getPixelForValue(point.x);
        const yPixel = scales.y.getPixelForValue(point.y);

        ctx.save();
        ctx.setLineDash([5, 5]);   // linea tratteggiata
        ctx.strokeStyle = 'red';
        ctx.lineWidth = 1;

        // Linea verticale (verso asse X)
        ctx.beginPath();
        ctx.moveTo(xPixel, yPixel);
        ctx.lineTo(xPixel, scales.y.bottom);
        ctx.stroke();

        // Linea orizzontale (verso asse Y)
        ctx.beginPath();
        ctx.moveTo(xPixel, yPixel);
        ctx.lineTo(scales.x.left, yPixel);
        ctx.stroke();

        ctx.restore();
    }
};


let chart = new Chart(ctx, {
    type: 'scatter',
    data: {
        datasets: [{
            label: 'Moody Curve',
            data: [],
            borderColor: '#444',
            showLine: true,
            fill: false
        },
        {
            label: 'Current Point',
            data: [],
            backgroundColor: 'red',
            pointRadius: 6
        }]
    },
    options: {
        scales: {
            x: {
                type: 'logarithmic',
                title: { display: true, text: 'Reynolds Number' }
            },
            y: {
                type: 'logarithmic',
                min: 0.006,        // 👈 QUI
                max: 0.1,          // (consigliato per Moody)
                title: { display: true, text: 'Friction Factor' }
            }
        }
    },
    plugins: [projectionLinesPlugin]   // 👈 aggiungi questo
});
document.getElementById('calc').addEventListener('click', () => {

    alert("Chiamata funzione ")

    const Re = parseFloat(document.getElementById('re').value);
    const rr = parseFloat(document.getElementById('rr').value);

    const f = computeFrictionFactor(Re, rr);

    alert(
        "DEBUG INFO\n\n" +
        "Reynolds = " + Re + "\n" +
        "Relative roughness = " + rr + "\n" +
        "Friction factor = " + f
    );

    document.getElementById('result').innerText =
        `f = ${f.toFixed(6)}`;

    updateDiagram(rr, Re, f);
});

function updateDiagram(rr, RePoint, fPoint) {

    const data = [];

    const minLog = 2;  // log10(100)
    const maxLog = 8;  // log10(1e8)

    for (let i = 0; i <= 200; i++) {

        const logRe = minLog + (maxLog - minLog) * (i / 200);
        const Re = Math.pow(10, logRe);

        const f = computeFrictionFactor(Re, rr);

        if (isFinite(f) && f > 0) {
            data.push({ x: Re, y: f });
        }
    }

    chart.data.datasets[0].data = data;

    if (fPoint > 0) {
        chart.data.datasets[1].data = [{ x: RePoint, y: fPoint }];
    }

    chart.update();
}
