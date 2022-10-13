import java.util.Vector;

/**
 * https://fr.wikipedia.org/wiki/R%C3%A9gression_lin%C3%A9aire_multiple
 */
public class RegLinMultiple {

    private Vector<Vector<Double>> matrix = new Vector<Vector<Double>>();

    public double estimateurAchapeau() {
        // TODO: réaliser (X^T*X)^-1 *X^T*Y ou X^T est la matrice transposer X
        return null;
    }

    /**
     * σ2 son estimateur sans biais des moindres carrés
     */
    public double sigmaCarre() {
        return 1 / (n - p - 1) * 1;
    }

    // Tableau d'analyse de variance et coefficient de détermination
    public double getSCE() {
        return 0;
    }

    public double getSCR() {
        return 0;
    }

    public double getSCT() {
        return getSCE() + getSCR();
    }

    public double Rcarre() {
        return getSCE() / getSCT();
    }

    public double Fcalc() {
        double r = Rcarre();
        return (r / p) / (1 - r) / (n - p - 1);
    }

    public String test() {
        return Fcalc() > F(p, n - p - 1, alpha - 1) ? "H0 : a1 = a2 = … = ap = 0 est vrai"
                : "H1 : un des coefficients au moins est non nul.";
    }
}
