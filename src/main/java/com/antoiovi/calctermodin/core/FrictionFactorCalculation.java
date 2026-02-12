
package com.antoiovi.calctermodin.core;

import com.antoiovi.unicig.condotti.MoodyDiagram;

/**
 * Computes the Darcy friction factor for internal pipe flow
 * using Reynolds number and relative roughness.
 *
 * Flow regimes:
 *  - Laminar (Re < 2300): analytical solution f = 64 / Re
 *  - Transitional (2300 <= Re <= 3400): linear interpolation
 *  - Turbulent (Re > 3400): Colebrook–White equation
 *
 * License: MIT
 */
public class FrictionFactorCalculation {

    public static final double RE_LAMINAR_LIMIT = 2300.0;
    public static final double RE_TURBULENT_LIMIT = 3400.0;

    public static Result calculate(double reynolds, double relativeRoughness) {

        validateInput(reynolds, relativeRoughness);

        if (reynolds < RE_LAMINAR_LIMIT) {
            return laminar(reynolds, relativeRoughness);
        }

        if (reynolds <= RE_TURBULENT_LIMIT) {
            return transitional(reynolds, relativeRoughness);
        }

        return turbulent(reynolds, relativeRoughness);
    }

    // ---------------------------
    // Flow Regimes
    // ---------------------------

    private static Result laminar(double re, double rr) {
        double f = 64.0 / re;
        return new Result(re, rr, f, FlowRegime.LAMINAR);
    }

    private static Result transitional(double re, double rr) {

        double fLam = 64.0 / re;

        MoodyDiagram moody = new MoodyDiagram(re, rr);
        double fTurb = moody.zbrent();

        double weight = (re - RE_LAMINAR_LIMIT) /
                        (RE_TURBULENT_LIMIT - RE_LAMINAR_LIMIT);

        double f = fLam + weight * (fTurb - fLam);

        return new Result(re, rr, f, FlowRegime.TRANSITIONAL);
    }

    private static Result turbulent(double re, double rr) {

        MoodyDiagram moody = new MoodyDiagram(re, rr);
        double f = moody.zbrent();

        return new Result(re, rr, f, FlowRegime.TURBULENT);
    }

    private static void validateInput(double re, double rr) {

        if (re <= 0)
            throw new IllegalArgumentException("Reynolds number must be > 0");

        if (rr < 0)
            throw new IllegalArgumentException("Relative roughness must be >= 0");
    }

    // ---------------------------
    // Result Object
    // ---------------------------

    public static class Result {

        public final double reynolds;
        public final double relativeRoughness;
        public final double frictionFactor;
        public final FlowRegime regime;

        public Result(double re, double rr, double f, FlowRegime regime) {
            this.reynolds = re;
            this.relativeRoughness = rr;
            this.frictionFactor = f;
            this.regime = regime;
        }
    }

    public enum FlowRegime {
        LAMINAR,
        TRANSITIONAL,
        TURBULENT
    }
}
