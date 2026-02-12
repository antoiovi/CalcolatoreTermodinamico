# OpenThermoFluids  
## Open Source Engineering Toolkit for Fluid Mechanics & Combustion Calculations  

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
![Java](https://img.shields.io/badge/Java-8%2B-blue)
![Status](https://img.shields.io/badge/Status-Active-green)

---
##  Documentation

- [GuiGuideda](docs/index.md)

## 🌍 Overview

**OpenThermoFluids** is an open-source engineering software toolkit written in Java, designed for:

- Fluid mechanics calculations
- Moody diagram friction factor evaluation
- Combustion analysis
- Chimney draft calculation
- Thermophysical properties of flue gases

The project aims to provide **transparent, reproducible, and professional-grade engineering tools** under the MIT License.

This software is intended for:

- Mechanical engineers
- HVAC designers
- Energy engineers
- Students and researchers
- Industrial professionals

---

# 📘 Modules

---

# 1️⃣ Moody Diagram – Friction Factor Calculator

### 🔬 Purpose

This module calculates the **Darcy-Weisbach friction factor** using:

- Laminar flow equation (f = 64/Re)
- Transitional interpolation
- Turbulent regime via **Colebrook-White equation**
- Numerical solution using Brent method

### 📐 Features

- Reynolds number based regime detection
- Relative roughness handling
- Colebrook equation solver
- Log-log diagram plotting
- Customizable line thickness and colors
- Engineering-grade numerical stability

### 🧮 Flow Regimes

| Reynolds Number | Regime |
|---------------|--------|
| Re < 2300 | Laminar |
| 2300 – 3400 | Transitional |
| Re > 3400 | Turbulent |

### 📊 Engineering Applications

- Pipe flow design
- Pressure drop calculations
- Hydraulic systems
- Oil & gas transport lines
- Water distribution networks

### 🖥 Screenshot

![Moody Diagram](docs/screenshots/moody-diagram.png)


### Keywords (SEO)

Moody Diagram, Colebrook Equation, Darcy Friction Factor, Fluid Mechanics Software, Pipe Flow Calculator, Hydraulic Engineering Tool

---

# 2️⃣ Boiler Combustion & Chimney Module  
### (UNI EN 13384-1 Implementation)

### 🔥 Purpose

This module calculates **thermophysical and combustion parameters of flue gases** according to:

UNI EN 13384-1 – Chimneys – Thermal and fluid dynamic calculation methods.

It models:

- Boiler combustion
- Flue gas composition
- Dew point temperature
- Minimum chimney draft
- Water vapor condensation conditions

---

## 📥 Inputs

- Fuel type
- Boiler thermal power
- Flue gas temperature
- Optional:
  - Flue gas mass flow
  - Efficiency
  - CO2 percentage
- Burner type:
  - Natural air
  - Forced air

---

## 📤 Outputs

- Flue gas mass flow [g/s]
- Specific heat capacity
- Water vapor content
- Partial pressure of water vapor
- Dew point temperature
- Dew point temperature increase
- Thermal conductivity
- Dynamic viscosity
- Gas constant
- Minimum required chimney draft
- Partial pressure at dew point

---


## Calculation Flow

1. **User Input**
2. **Input Validation**
3. **Boiler Model Computation (Gener)**
4. **Combustion Properties Evaluation (Comb_2)**
5. **Thermodynamic Processing**
6. **GUI Output Rendering**

---

## 📚 Engineering Context

This module is suitable for:

- Chimney sizing
- Condensation risk evaluation
- Boiler efficiency diagnostics
- Flue gas analysis
- HVAC system design
- Energy system simulations

### Keywords (SEO)

Combustion Calculation Software, Chimney Draft Calculator, UNI EN 13384, Flue Gas Analysis, HVAC Engineering Tool, Boiler Simulation, Thermophysical Properties Calculator

---

# 🏗 Architecture

- Java Swing GUI
- Modular structure
- Separate calculation core
- Numerical methods implementation
- Expandable engineering framework

Future goal:

Core calculation engine fully separated from GUI  
CLI version  
Web-based version  
REST API integration  

---

# 📦 Installation

Clone the repository:


Open in your preferred IDE:

- IntelliJ IDEA
- Eclipse
- NetBeans

Requires:

- Java 8 or higher

---

# 🎯 Vision

The goal of this project is to build a:

> Free, transparent, open engineering calculation platform  
> Accessible worldwide  
> Reproducible and verifiable  
> Educational and professional  

We believe engineering tools should be:

- Open
- Inspectable
- Modifiable
- Trustworthy

---

# 🤝 Contributing

Contributions are welcome.

You can:

- Improve numerical methods
- Refactor architecture
- Add unit tests
- Implement additional standards
- Create documentation
- Develop UI improvements
- Translate to other languages

---

# 📄 License

MIT License

This project is released under the MIT License.  
You are free to use, modify, distribute, and integrate it into commercial software.

---

# 🌎 Why Open Source Engineering Software?

Many engineering tools are:

- Expensive
- Closed source
- Not reproducible
- Opaque in calculation methods

OpenThermoFluids aims to change this.

Transparent formulas.  
Documented standards.  
Professional numerical methods.  
Free global access.

---

# 🚀 Roadmap

- Separate GUI from calculation core
- Add automated test suite
- Add unit system selection (SI / Imperial)
- Add multi-language support
- Create web interface
- Publish technical blog documentation
- Build community around open engineering computation

---

# ⭐ Support the Project

If you find this project useful:

- Star the repository
- Share it
- Contribute
- Write about it
- Use it in research or education

---

## Engineering should be open.
## Knowledge should be shared.
## Tools should be accessible.
