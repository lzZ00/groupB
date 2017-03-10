
import java.util.Random;

public class CodeSecret {
    private boolean revele;
    private String code;
    private int erreurCode;

    public static CodeSecret createCode(Random random) {
        String pin;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append(random.nextInt(10));
        }
        pin = builder.toString();
        return new CodeSecret(pin);
    }


    private CodeSecret(String code) {
        this.code = code;
        revele=false;
        erreurCode = 0;
    }

    public String revelerCode() {
        if (!revele) {
            revele = true;
            return code;
        }
        return "xxxx";
    }

    public boolean verifierCode(String code) throws CodeBloqueException {
        if (erreurCode>=3) throw new CodeBloqueException();

        if(this.code.equals(code)) {
            erreurCode=0;
            return true;
        } else {
            erreurCode++;
            return false;
        }

    }
}
