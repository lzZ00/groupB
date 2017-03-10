import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PurseUnitTest {

    @Test
    public void testCredit() throws Exception {
        Purse purse = new Purse(100, 500);
        double solde = purse.getSolde();
        purse.credite(20.0);
        Assert.assertEquals(solde+20, purse.getSolde(), 0);
    }

    @Test
    public void testDebit() throws Exception {
        Purse purse = new Purse(100, 500);
        purse.credite(20);
        double solde = purse.getSolde();
        purse.debitAutorise(20.0);
        Assert.assertEquals(solde-20, purse.getSolde(), 0);
    }

    @Test (expected = SoldeJamaisNegatifException.class)
    public void testDebitImpossibleSoldeToujoursPositif() throws Exception {
        Purse purse = new Purse(100, 500);
        purse.debitAutorise(purse.getSolde()+1);
    }

    @Test (expected = DepassementDePlafondException.class)
    public void testCreditImpossibleDepassementPlafond() throws Exception {
        Purse purse = new Purse(10.0, 500);
        purse.credite(20);
    }

    @Test (expected = MontantNegatifException.class)
    public void testCreditImpossibleMontantNegatif() throws Exception {
        Purse purse = new Purse(100, 500);
        purse.credite(-1);
    }

    @Test (expected = MontantNegatifException.class)
    public void testDebitImpossibleMontantNegatif() throws Exception {
        Purse purse = new Purse(100, 500);
        purse.debitAutorise(-1);
    }

    @Rule
    public ExpectedException toto = ExpectedException.none();

    @Test
    public void testFinDeVieSurDebit() throws Exception {
        Purse purse = new Purse(100.0, 3);
        purse.credite(50);
        purse.debitAutorise(25);
        purse.credite(20);
        toto.expect(FinDeVieException.class);
        purse.debitAutorise(10);
    }

    @Test
    public void testFinDeVieSurCredit() throws Exception {
        Purse purse = new Purse(100.0, 3);
        purse.credite(50);
        purse.debitAutorise(25);
        purse.credite(20);
        toto.expect(FinDeVieException.class);
        purse.credite(10);
    }


}

