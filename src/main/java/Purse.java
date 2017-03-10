
public class Purse {

    private double solde;
    private double plafond;
    private int nombreOperationsPossibles;

    public Purse(double plafond, int nombreOperationsPossibles) {
        this.solde = 0;
        this.plafond = plafond;
        this.nombreOperationsPossibles = nombreOperationsPossibles;
    }

    public double getSolde() {
        return solde;
    }

    public void credite(double montant)
            throws DepassementDePlafondException,
            MontantNegatifException,
            FinDeVieException {
        if (montant <= 0) throw new MontantNegatifException();
        if (nombreOperationsPossibles <= 0)
            throw new FinDeVieException();
        if (montant + solde > plafond) throw new DepassementDePlafondException();
        solde += montant;
        nombreOperationsPossibles--;
    }

    public void debitAutorise(double montant)
            throws SoldeJamaisNegatifException,
            MontantNegatifException,
            FinDeVieException {
        if (montant <= 0) throw new MontantNegatifException();
        if (nombreOperationsPossibles <= 0) throw new FinDeVieException();
        if (montant > solde) throw new SoldeJamaisNegatifException();
        solde -= montant;
        nombreOperationsPossibles--;
    }
}
