
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Random;

public class CodeSecretUnitTest {

    @Test
    public void testRevelerCode() {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(10)).thenReturn(0,1,2,3);
        CodeSecret codeSecret = CodeSecret.createCode(rand);
        String code = codeSecret.revelerCode();
        Assert.assertTrue(isCode(code));
        Assert.assertEquals("0123",code);

        Assert.assertEquals("xxxx",codeSecret.revelerCode());
    }

    private boolean isCode(String code) {
        if (code.length() != 4) return false;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i)<'0' || code.charAt(i)>'9') return false;
        }
        return true;
    }

    @Test
    public void testVerifierCodeVrai() throws CodeBloqueException {
        CodeSecret codeSecret = CodeSecret.createCode(new Random());
        String code = codeSecret.revelerCode();
        Assert.assertTrue(codeSecret.verifierCode(code));
    }

    @Test
    public void testVerifierCodeFaux() throws CodeBloqueException {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(10)).thenReturn(0,1,2,3);
        CodeSecret codeSecret = CodeSecret.createCode(rand);
        Assert.assertFalse(codeSecret.verifierCode("1234"));
    }

    @Rule
    public ExpectedException toto1 = ExpectedException.none();

    @Test
    public void testVerifierCodeEssaisReinitialiseA3ApresCodeJusteEtCodeBloqueApres3essaisFaux() throws Exception {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(10)).thenReturn(0,1,2,3);
        CodeSecret codeSecret = CodeSecret.createCode(rand);
        codeSecret.verifierCode("1234");
        codeSecret.verifierCode("1234");
        codeSecret.verifierCode("0123");
        codeSecret.verifierCode("1234");
        codeSecret.verifierCode("1234");
        codeSecret.verifierCode("1234");
        toto1.expect(CodeBloqueException.class);
        codeSecret.verifierCode("0123");
    }
}
