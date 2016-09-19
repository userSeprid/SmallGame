package MainPack;


enum RaceType {
    Human(0,0,0,0), Orc(3,0,0,0), Dwarf(1,2,0,0), Elf(0,0,2,2), Fairy(0,0,0,4);

    int bonusToStr;
    int bonusToCon;
    int bonusToAgi;
    int bonusToMag;


    RaceType(int s, int c, int a, int m) {
        bonusToStr = s;
        bonusToCon = c;
        bonusToAgi = a;
        bonusToMag = m;
    }

    //TODO : Bonuses for races
    //TODO: Human +20% to earned exp ?
    //TODO: Orc +3 Str +
    //TODO: Dwarf +2 Con / +1 Str +
    //TODO: Elf +3 Mag / +2 Agi ?
    //TODO: Fairy +4 Mag / -1 Con ?
}
