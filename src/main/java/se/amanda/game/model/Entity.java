package se.amanda.game.model;

public abstract class Entity {
    private String role;
    private int health;
    private int damage;

    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public String getRole() {
        return role;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void Punch(Entity toPunch){
        System.out.println(this.role + " skadar. " + toPunch.getRole() + " hälsa kvar: " + this.health);

        toPunch.takeHit(this.damage);
    }

    public void takeHit(int damage){
        this.health -= damage;
        System.out.println(this.role + " tar " + damage + " skada. Hans hälsa ligger nu på " + this.health);

        if (this.health <= 0) {
            System.out.println(this.role + " har förlorat slagsmålet.");
        }
    }

    public boolean isConscious() {
        return this.health > 0;
    }

    public void addDamage(int damage) {
        this.damage += damage;
    }
}
