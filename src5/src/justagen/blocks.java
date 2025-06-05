package justagen;

import static justagen.colours.*;

public enum blocks {
    SKY (ANSI_BG_BLUE + "  " + ANSI_RESET),
    STONE (ANSI_BG_BRIGHT_BLACK + "  " + ANSI_RESET),
    BEDROCK (ANSI_BG_BLACK + "  " + ANSI_RESET),
    GRASS (ANSI_BG_GREEN + "  " + ANSI_RESET),
    LAVA (ANSI_BG_ORANGE + "  " + ANSI_RESET),
    LOG(ANSI_BG_BROWN + "  " + ANSI_RESET),
    CAVEAIR (ANSI_BG_WHITE + "  " + ANSI_RESET),
    LEAVES (ANSI_BG_DARK_GREEN + "  " + ANSI_RESET),
    BEENEST (ANSI_BG_YELLOW + "  " + ANSI_RESET),
    SUN (ANSI_BG_BRIGHT_YELLOW + "  " + ANSI_RESET),
    PLAYER (ANSI_BG_WHITE + "P " + ANSI_RESET),
    BORDERBLOCK ("  ");

    private String printedColour;

    blocks(String printedColour){
        this.printedColour = printedColour;
    }

    public String getPrintedColour(){
        return printedColour;
    }


}
