package MainPack.ServerPart;


enum Profession {
    Dampir(5, 22, 8), Paladin(6, 17, 12), Berserk(10, 500, 6);

    int str;
    int con;
    int agi;

    Profession(int s, int c, int a)
    {
        str = s;
        con = c;
        agi = a;
    }
}
