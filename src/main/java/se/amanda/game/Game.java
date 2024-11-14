package se.amanda.game;
import se.amanda.game.model.Burglar;
import se.amanda.game.model.Resident;
import java.util.Scanner;

    public class Game {
                private static final String VARDAGSRUM = "Vardagrum";
                private static final String KÖK = "Kök";
                private static final String SOVRUM = "Sovrum";
                private static final String HALL = "Hall";
                private static final String KONTOR = "Kontor";

                private String currentLocation = VARDAGSRUM;
                private Resident resident;
                private Burglar burglar;
                private boolean fryingPanFound = false;
                private boolean running = true;
                private boolean callPolice = false;

                public Game() {
                    resident = new Resident("Boende", 12, 3);
                    burglar = new Burglar("Inbrottstjuv", 12, 4);
                }

                public void start() {
                    System.out.println("Spelet har börjat, du har somnat och vaknar av ett högt ljud, någon har brutit sig in.");

                    while (running && resident.isConscious()) {
                        location();
                        happenings();
                        if (!running) break;
                        navigate();
                    }
                    System.out.println("Spelet är slut.");
        }

        private void location() {
            String description = switch (currentLocation) {
                case VARDAGSRUM -> "Du vaknar upp i vardagsrummet. Det är mörkt. ";
                case KÖK -> "Du är i köket och hittar en stekpanna, vill du plocka upp den? Skriv in ja/nej.";
                case SOVRUM -> "Du är i sovrummet, det finns ingen här.";
                case HALL -> "Du är i hallen och möter på en inbrottstjuv";
                case KONTOR -> {
                    if (!burglar.isConscious()) {
                        yield "Du kommer till kontoret. Vill du ringa polisen för att avsluta spelet? Skriv in ja/nej.";
                    } else {
                        yield "Du kommer till kontoret men du måste knocka inbrottstjuven innan du kan ringa polisen.";
                    }
                }
                default -> "Ogiltigt kommando.";
            };
            System.out.println(description);
        }

        private void happenings() {
            if (currentLocation.equals(KÖK) && !fryingPanFound) {
                takeFryingPan();
            } else if (currentLocation.equals(HALL)) {
                startFight();
            } else if (currentLocation.equals(KONTOR) && !burglar.isConscious() && !callPolice) {
                policeCall();
            }
        }

        private void takeFryingPan() {
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("ja")) {
                System.out.println("Du har tagit stekpannan och din damage ökar med 3.");
                resident.addDamage(3);
                fryingPanFound = true;
            } else {
                System.out.println("Du valde ej att ta stekpannan");
            }

        }

        private void policeCall() {
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("ja")) {
                endGame();
                callPolice = true;
            } else {
                System.out.println("Du valde ej att ringa polisen.");
            }
        }

        private void startFight() {
            System.out.println("Du möter inbrottstjuven, ni hamnar i ett slagsmål.");

            while (resident.isConscious() && burglar.isConscious()) {
                resident.Punch(burglar);
                if (!burglar.isConscious()) {
                    System.out.println("Ta dig till kontoret för att ringa polisen.");
                    break;
                }

                burglar.Punch(resident);
                if (!resident.isConscious()) {
                    System.out.println("Du förlorade, spelet avslutas.");
                    break;
                }
            }
        }


        private void endGame() {
            System.out.println("Du kontaktar polisen och spelet avslutas.");
            running = false;
        }

        private void navigate() {
            if (!running) return;

            Scanner scanner = new Scanner(System.in);
            System.out.println("Vart vill du gå? Du kan välja mellan Vardagsrum, Kök, Sovrum, Hall och Kontor.");
            String choice = scanner.nextLine().toLowerCase();

            currentLocation = switch (choice) {
                case "vardagsrum" -> VARDAGSRUM;
                case "kök" -> KÖK;
                case "sovrum" -> SOVRUM;
                case "hall" -> HALL;
                case "kontor" -> KONTOR;
                default -> {
                    System.out.println("Ogiltigt kommando försök igen.");
                    yield currentLocation;
                }
            };
        }
    }








