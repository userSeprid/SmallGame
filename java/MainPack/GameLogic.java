package MainPack;


import java.util.Random;

class GameLogic {





    void makeAHero(int position)
        {
            if (position == 1) {player = new Hero(5, 22, 8, "Uter", 0, 1, 1);
            } else if (position == 2) {player = new Hero(6, 17, 12, "Muradin", 0, 1, 1);
            } else if (position == 3) {player = new Hero(10, 500, 6, "Ragnor", 0, 1, 1);
            }

        }

    void makeAMonster()
    {
        Random rand = new Random();

        enemy = new Monster(8 + rand.nextInt(3), 15 + rand.nextInt(4), 2 + rand.nextInt(2), "Bakemono");
    }




    Hero getPlayer() {
        return player;
    }

    Monster getEnemy() {
        return enemy;
    }


    private Hero player;
    private Monster enemy;


}
