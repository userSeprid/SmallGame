package MainPack;


import java.io.Serializable;

public class HeroData implements Serializable{

    private RaceType race;
    private Profession aClass;
    private String name;

    public void setAClass(Profession unitProf) {
        aClass = unitProf;
    }

    public void setRace(RaceType race) {
        this.race = race;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaceType getRace() {
        return race;
    }

    public Profession getaClass() {
        return aClass;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "HeroData{" +
                "race=" + race +
                ", aClass=" + aClass +
                ", name='" + name + '\'' +
                '}';
    }
}
