# Thermodynamics Calculator

There are two functionalities :

	- Panle 1 : Plots the Moody diagram and claculate the friction factor;
	- Panel 2 : Calcluate chemical properties product of combustion as specified by UNI EN 13384-1

![alt text](https://github.com/antoiovi/CalcolatoreTermodinamico/blob/master/Mody-01.png?raw=true)

## Execute demo
	
	mvn compile

Linux

	   ./demo.sh

Windows

	  execdemo.bat

## Execute

Java version 1.8 (tested)

	mvn clean package


	java -jar calctermodin-2.jar 


## Panel Moody Diagram 
# Moody Diagram – Darcy Friction Factor Calculator (Java)

Open-source Java application for computing the **Darcy friction factor**
using the **Moody diagram** and the **Colebrook–White equation**.

Licensed under the MIT License.

---

## 🌍 Project Goal

This project aims to provide a free, open-source and educational tool
for students and engineers worldwide to:

- Compute Darcy friction factor
- Visualize results on a Moody diagram
- Understand flow regime transitions
- Explore the impact of relative roughness

---

## 📌 Features

- Laminar flow calculation (f = 64 / Re)
- Transitional regime interpolation (2300 ≤ Re ≤ 3400)
- Turbulent flow solution via Colebrook–White equation
- Logarithmic Moody diagram visualization
- Relative roughness (ε/D) support
- Interactive GUI (Java Swing)

---

## 🧮 Flow Regimes

| Regime        | Reynolds Number Range |
|--------------|----------------------|
| Laminar      | Re < 2300            |
| Transitional | 2300 – 3400          |
| Turbulent    | Re > 3400            |

---

## 📊 Equations Used

### Laminar Flow
f = 64 / Re

### Turbulent Flow (Colebrook–White)
1/√f = -2 log10( (ε/D)/3.7 + 2.51/(Re√f) )

---

## 🖥 Screenshot

![Moody Diagram](docs/screenshots/moody-diagram.png)

---

## 🚀 Getting Started

### Requirements

- Java 8+

### Compile & Run

```bash

Java version 1.8 (tested)
mvn clean packages
java -jar calctermodin-2.jar 
```

📚 References

Moody, L. F. (1944). Friction factors for pipe flow.

Colebrook, C. F. (1939). Turbulent flow in pipes.

🤝 Contributing

Contributions are welcome!

Please open an issue to discuss improvements or submit a pull request.


MIT License – free for academic and commercial use.


Copyright (c) 2026 Antonello Iovino 

Permission is hereby granted, free of charge...

## Roadmap

- [ ] Separate core calculation engine from GUI
- [ ] Add CLI version
- [ ] Add unit selection (SI / Imperial)
- [ ] Web-based version (future)
- [ ] Publish on Maven Central

## Panel Combustibile
Calcluate chemical properties product of combustion as specified by UNI EN 13384-1
