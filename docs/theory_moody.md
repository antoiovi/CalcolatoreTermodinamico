 Theory: Moody Diagram and Darcy Friction Factor

This document explains the theory behind the **Darcy friction factor** and
the **Moody diagram**, used in this software to calculate 
pressure losses in pipe flow.

---

## 1. Introduction

The **Darcy friction factor** (f) is a dimensionless quantity used to 
estimate the **pressure drop** or **head loss** due to friction in 
pipes. It depends on:

- The **flow regime** (laminar, transitional, turbulent)
- The **Reynolds number** (Re)
- The **relative roughness** of the pipe (ε/D)

---

## 2. Reynolds Number (Re)

\[
\text{Re} = \frac{\rho V D}{\mu}
\]

- **Laminar flow:** Re < 2300  
- **Transitional flow:** 2300 ≤ Re ≤ 3400  
- **Turbulent flow:** Re > 3400  

---

## 3. Relative Roughness (ε/D)

\[
\text{Relative roughness} = \frac{\epsilon}{D}
\]

- Smooth pipes: ε/D ≈ 0  
- Rough pipes: ε/D > 0.001  

---

## 4. Darcy Friction Factor (f)

\[
\Delta P = f \frac{L}{D} \frac{\rho V^2}{2}
\]

- ΔP: Pressure drop  
- L: Pipe length  
- D: Pipe diameter  
- ρ: Fluid density  
- V: Velocity  

---

### 4.1 Laminar Flow

\[
f = \frac{64}{\text{Re}}
\]

- Linear on a log-log plot of f vs Re  

### 4.2 Turbulent Flow

\[
\frac{1}{\sqrt{f}} = -2 \log_{10} \left( \frac{\epsilon/D}{3.7} + \frac{2.51}{\text{Re} \sqrt{f}} \right)
\]

- Solved numerically (e.g., Brent's method)  
- Displayed as curves in Moody diagram  

### 4.3 Transitional Flow

\[
f_\text{trans} = f_\text{laminar} + \frac{\text{Re} - 2300}{3400 - 2300} \left(f_\text{turbulent} - f_\text{laminar}\right)
\]

---

## 5. Moody Diagram 

The **Moody Diagram** is a fundamental tool in fluid mechanics, used to determine the **Darcy-Weisbach friction factor (f)** for flow in a circular pipe based on:

- **Reynolds number (Re)** – dimensionless number representing flow regime
- **Relative roughness (ε/D)** – ratio of pipe roughness to diameter

The diagram covers **three main flow regimes**:

| Flow regime       | Reynolds number range |
|------------------|--------------------|
| Laminar           | Re < 2300          |
| Transitional      | 2300 ≤ Re < 3400  |
| Turbulent         | Re ≥ 3400          |

---

## How to Read the Diagram

1. Locate the **Reynolds number** along the horizontal axis (logarithmic scale).
2. Identify the **relative roughness** curve corresponding to your pipe.
3. Move vertically to intersect the chosen curve.
4. Read the **friction factor f** on the vertical axis (also logarithmic).

---

## Flow Regimes Visualization

Below is a **schematic representation of flow regimes**:

| Reynolds number → | 0–2300       | 2300–3400       | >3400       |
|------------------|-------------|----------------|------------|
| Friction factor f | Laminar     | Transitional   | Turbulent  |

> In laminar flow, \( f = 64 / Re \).  
> In transitional flow, \( f \) is interpolated between laminar and turbulent.  
> In turbulent flow, \( f \) is calculated via the Colebrook equation or read from the Moody Diagram.

---

## Example Moody Diagram

![Moody Diagram](screenshots/moody-diagram.png)


> The figure above shows curves of friction factor \( f \) versus Reynolds number for various relative roughness values.  

---
## Flowchart ( APanelMoodyDiagram.java )

flowchart TD

A[User clicks Calculate] --> B[Read Reynolds number]
B --> C[Compute or read relative roughness]

C --> D{Re < 2300?}

D -- Yes --> E[Laminar flow\nf = 64/Re]
D -- No --> F{Re < 3400?}

F -- Yes --> G[Transitional flow\nLinear interpolation]
F -- No --> H[Turbulent flow\nColebrook-White]

E --> I[Update friction factor]
G --> I
H --> I

I --> J[Update text output]
J --> K[Update Moody diagram point]
K --> L[Repaint diagram]


## References

- F.M. White, *Fluid Mechanics*, 8th Edition, McGraw-Hill, 2016  
- Moody, L.F., "Friction Factors for Pipe Flow," *Transactions of the ASME*, 1944


## 6. Software Implementation Notes

- Input: Re, D, ε, or ε/D  
- Output: Friction factor f and flow regime  
- Graphical: Moody diagram with calculated point and relative roughness curve  
- Numerical method: Brent's method to solve Colebrook equation  

---

## 7. References

1. Moody, L. F. (1944). *Friction factors for pipe flow*. Transactions of the ASME, 66(8).  
2. Colebrook, C. F. (1939). *Turbulent flow in pipes, with particular reference to the transition region*.  
3. White, F. M. (2011). *Fluid Mechanics*, 7th Edition. McGraw-Hill.  

---

*This document supports the open-source Moody Diagram Java application.*

