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

## 5. Moody Diagram (Visual ASCII)

Below is a **simplified diagram** to understand zones and relative roughness:

f
| Turbulent Flow (f ≈ 0.02–0.08)
| **** ***** ***** *****
| * * * * *
|* * * * *
| * *
|----------------------------> Re
| Laminar Transizione Turbulento
| (Re<2300) (2300–3400) (Re>3400)


**Legend:**

- Laminar: straight line (f = 64/Re)  
- Transition: shaded/interpolated region  
- Turbulent: multiple curves for different ε/D  

Another view with **roughness effect**:

f
| 0.08 +-------------------+ Rough pipe (ε/D = 0.01)
| | ***** |
| 0.04 +------------------+ Medium roughness (ε/D = 0.005)
| | * |
| 0.02 +------------------+ Smooth pipe (ε/D ≈ 0)
|-----------------------------> Re
1e3 1e4 1e5


- X-axis: Reynolds number (log scale)  
- Y-axis: Friction factor f (log scale)  
- Each curve: a different relative roughness  

---

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

