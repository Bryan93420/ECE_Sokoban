


public class Case {




    public static final int SOL = 0;
    public static final int MUR = 1;
    public static final int CAISSE = 2;
    public static final int CAISSE_PLACEE = 3;
    public static final int GOAL = 4;
    public static final int PERSO = 5;
    public static final int PERSO_GOAL = 6;

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    private int etat;

    private Plateau plateau;

    public Case(Plateau p, int Case){
        this.plateau = p;
        this.etat = Case;
    }

    public void setCase_actuelle(int newCase) {
        etat = newCase;

    }



    public Case(Case c) {
        setCase_actuelle(c.getCase_actuelle());
    }



    public int getSOL() {
        return SOL;
    }

    public int getMUR() {
        return MUR;
    }

    public int getCAISSE() {
        return CAISSE;
    }

    public int getCAISSE_PLACEE() {
        return CAISSE_PLACEE;
    }

    public int getGOAL() {
        return GOAL;
    }

    public int getPERSO() {
        return PERSO;
    }

    public int getPERSO_GOAL() {
        return PERSO_GOAL;
    }

    public int getCase_actuelle() {
        return etat;
    }

}
