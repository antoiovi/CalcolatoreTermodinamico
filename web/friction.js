// friction.js
// MIT License

export function computeFrictionFactor(Re, relRough) {

    if (Re <= 0) throw new Error("Invalid Reynolds number");

    // Laminar
    if (Re < 2300) {
        return 64.0 / Re;
    }

    // Transitional
    if (Re <= 3400) {
        const fLam = 64.0 / Re;
        const fTurb = colebrook(Re, relRough);
        const w = (Re - 2300) / (3400 - 2300);
        return fLam + w * (fTurb - fLam);
    }

    // Turbulent
    return colebrook(Re, relRough);
}


// Colebrook-White solved with Newton-Raphson
function colebrook(Re, relRough) {

    let f = 0.02; // initial guess

    for (let i = 0; i < 50; i++) {

        const g = 1 / Math.sqrt(f) +
            2.0 * Math.log10(
                relRough / 3.7 + 2.51 / (Re * Math.sqrt(f))
            );

        const dg =
            (-0.5) * f ** (-3 / 2)
            - (2.0 * 2.51) /
            (Math.log(10) * Re * 2 * f * Math.sqrt(f));

        const fNew = f - g / dg;

        if (Math.abs(fNew - f) < 1e-10)
            return fNew;

        f = fNew;
    }

    return f;
}
