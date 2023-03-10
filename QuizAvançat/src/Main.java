public class Main {

    static final String NAME = "QUIZ";
    public static void main(String[] args) {
        String[][] questions = new String[3][15];
        //counters
        int nQuestions = 0, correct, category = 0, incorrect = 0;
        //numbers for the questions
        int position = 0, max, min = 5;
        boolean [] askedQuestions;
        boolean game = true, gameOver = false, bonus = false;
        boolean [] incorrectAnswers;
        boolean [] scoreBonus;

        //reservar memoria dels arrays declarats en la seva propia funció
        incorrectAnswers = setIncorrectAnswers();
        scoreBonus = setScoreBonus();

        do {

            gameOver = gameStart(gameOver, incorrectAnswers);

            correct = 0;

            questions = arrayQuestions(category, questions);
            max = questions[0].length;
            //boolean with the max number of questions there are, so you can know which ones you already asked
            askedQuestions = new boolean[max];

            nQuestions = numberOfQuestions(min, max, nQuestions);


            //loop for each question -> en una funció
            for (int i = 0; i < nQuestions && gameOver == false; i++) {

                //function to not repeat a question
                position = questionPosition(position, max, askedQuestions);

                //funtion to answer the question and make sure the answer is yes or no.
                correct = askingQuestion(questions, position, correct, incorrectAnswers, scoreBonus);

                //gameOver
                gameOver = checkGameOver(incorrectAnswers, gameOver);

            }

            bonus = checkBonus(scoreBonus, bonus);
            finalMessage(nQuestions, correct, bonus);



            game = gameOn(game);

        }while (game);

    }
    private static boolean[] setIncorrectAnswers(){
        boolean[] incorrectAnswers = new boolean[3];
        return incorrectAnswers;
    }
    private static boolean[] setScoreBonus(){
        boolean[] scoreBonus = new boolean[3];
        return scoreBonus;
    }
    private static boolean gameStart(boolean gameOver,boolean[] incorrectAnswers){
        gameOver = false;
        for (int i = 0; i < incorrectAnswers.length; i++) {
            incorrectAnswers[i] = false;
        }
        return gameOver;
    }
    private static String[][] arrayQuestions1() { //anime
        String[][] questions = {{"What power does Izuku Midoriya have from birth in My Hero Academia?\nOne for all\nNone\nAll for one", "\n" +
                "How many districts can we find in total in Shingeki no kyojin?\n25\n8\n13",
                "Who is the leader and founder of SSS Shinda Sekai Sensen in Angel Beats?\nYuri Nakamura\nEri Shiina\nMiyuki Irie",
                "What are the eyes of a Ghoul called in Tokyo Ghoul?\nKakugan\nBakugan\nBokugan",
                "In Sailor Moon, who is the Guardian of Dreams?\nAya Hisakawa\nSeiya Kou\nPegasus",
                "Where does Naruto live?\nKonoha\nAlabasta\nNamek",
                "What is the name of Meliodas' sword in The Seven Deadly Sins?\nGreen dragon sword\nBroken sword\nLight sword",
                "In Haikyuu, whose nickname is \"The King of the Track\"?\nHinata Shoyo\nToru Oikawa\nTobio Kageyama",
                "What does the word Shinigami mean?\nGod of Death\nSoul Eater\nDeath Note Protector"},
                {"NONE", "13", "YURI NAKAMURA", "KAKUGAN", "PEGASUS", "KONOHA", "BROKEN SWORD", "TOBIO KAGEYAMA", "GOD OF DEATH"}};
        return questions;
    }
    private static String[][] arrayQuestions2() { //Dragon Ball
        String[][] questions = {{"Who was the creator of Dragon Ball?\nHayao Miyazaki\nTakeshi Kitano\nAkira Toriyama",
        "What is the name of the god of destruction?\nChampa\nJiren\nBeerus",
        "What is the name of the transformation of Goku and Vegeta with the Pottara?\nGogeta\nGotrunks\nVegetto",
        "What are the names of the two android brothers?\nA17 and A19\nA16 and A20\nA17 and A18",
        "What is the technique that Goku learns with the Kaio?\nKaio-Ten\nKaio-Ken\nKai-Ken",
        "How many transformations does Cell have (not counting base form)?\n5\n2\n3",
        "What is the name of Goku's brother?\nRackon\nRaditz\nNappa",
        "What is the name of Goku's first son?\nGoten\nGohan\nGochan",
        "What is the name of Goku's wife?\nChi-chi\nMaron\nCarlote",
        "What is the name of Goku's father?\nTurlest\nBardock\nKakaroto"},
            {"AKIRA TORIYAMA", "BEERUS", "VEGETTO", "A17 AND A18", "KAIO-KEN", "3", "RADITZ", "GOHAN", "CHI-CHI", "BARDOCK"}};
        return questions;
    }
    private static String[][] arrayQuestions3() { //Studio Ghibli
        String[][] questions = {{"In which movie was a different script created for the same movie, one version for Ghibli and one for Disney?\nArriety and the world of the tiny\n" +
                "The red turtle\nPrincess Mononoke", "What was the first movie to use Dolby Digital in Japan?\nTales from Earthsea\nWhispers of the heart\nThe wind rises",
                "What is the real name of Porco Rosso, which was given as a tribute to the Calimero cartoonists?\nZassaou Note\nMarco Pagot\nPorco Dio",
                "What was the first movie that Disney got the rights to distribute?\nMy neighbors the Yamadas\nKiki's Delivery Service\nFrom poppy hill",
                "What is the only Ghibli movie that has Live Action?\nPrincess Mononoke\nTales from Earthsea\nThe fireflies's grave",
                "Who voices Howl in the English version?\nChristian Bale\nDaniel Radcliffe\nMartin Freeman",
                "What was the first official Studio Ghibli movie?\nNausicaä of the Valley of the Wind\nThe castle in the sky\nTotoro",
                "What is the original color of Howl's hair?\nBlond\nRedhead\nBlack", "What is the studio's longest running movie, with a running time of 2 hours and 17 minutes?\nSpirited Away\nPorco rosso\nPrincess Kaguya",
                "5 Studios Ghibli films were nominated for Oscars, but only one film got it. Which?\nPrincess Mononoke\nSpirited Away\nThe wind rises"},
                {"PRINCESS MONONOKE", "WHISPERS OF THE HEART", "MARCO PAGOT", "KIKI'S DELIVERY SERVICE", "THE FIREFLIES'S GRAVE", "CHRISTIAN BALE", "THE CASTLE IN THE SKY", "BLOND", "PRINCESS KAGUYA", "SPIRITED AWAY"}};
        return questions;
    }

    private static String[][] arrayQuestions(int category, String[][] questions){
        category = questionCategory();
        switch (category){
            case 1 -> questions = arrayQuestions1();
            case 2 -> questions = arrayQuestions2();
            case 3 -> questions = arrayQuestions3();
        }
        return questions;
    }
    private static int questionCategory(){
        int category;
        System.out.println("Which category do you want to play?\n1- Anime\n2- Dragon Ball\n3- Studio Ghibli");
        category = Teclat.llegirInt();

        return category;
    }
    private static int numberOfQuestions(int min, int max, int nQuestions){
        do{
            System.out.println("How many questions do you want? Between "+min+" and "+max);
            nQuestions = Teclat.llegirInt();
        }while(nQuestions < min || nQuestions > max);
        return nQuestions;
    }
    private static int questionPosition(int position, int max, boolean[] askedQuestions){
        do{

            position = (int)(Math.random()*((max - 2) + 1)) + 1;

        }while(askedQuestions[position]);

        askedQuestions[position] = true;

        return position;
    }
    private static int askingQuestion(String[][] questions, int position, int correct, boolean[] incorrectAnswers, boolean[] scoreBonus){
        //String with the users answer
        String answer;

            System.out.println(questions[0][position]);
            answer = Teclat.llegirString().toUpperCase();

            if(answer.equals(questions[1][position])) {
                System.out.println("Correct answer!\n");
                correct++;
                checkScoreBonus(scoreBonus);
                for (int i = 0; i < incorrectAnswers.length; i++) {
                    incorrectAnswers[i] = false;
                }
            }else{
                System.out.println("Incorrect answer...\n");
                checkIncorrectAnswers(incorrectAnswers);
            }

        return correct;
    }
    private static void checkScoreBonus(boolean[] scoreBonus){
        if(scoreBonus[0] == false){
            scoreBonus[0] = true;
        } else if (scoreBonus[1] == false) {
            scoreBonus[1] = true;
        } else if (scoreBonus[2] == false) {
            scoreBonus[2] = true;
        }
    }
    private static boolean checkBonus(boolean[] scoreBonus, boolean bonus){
        if(scoreBonus[2] == true){
            bonus = true;
        }
        return bonus;
    }
    private static void checkIncorrectAnswers(boolean[] incorrectAnswers){
        if(incorrectAnswers[0] == false){
            incorrectAnswers[0] = true;
        } else if (incorrectAnswers[1] == false) {
            incorrectAnswers[1] = true;
        } else if (incorrectAnswers[2] == false) {
            incorrectAnswers[2] = true;
        }
    }
    private static boolean checkGameOver(boolean[] incorrectAnswers, boolean gameOver){
        if(incorrectAnswers[2] == true){
            gameOver = true;
            System.out.println("GAME OVER");
        }

        return gameOver;
    }

    //function, send a diferent message depending on the percentage of correct answers
    static int BONUS = 10;
    private static void finalMessage (int nQuestions, int correct, boolean bonus){
        int percentage = (correct*100)/nQuestions;

        System.out.println("You answered "+percentage+"% of the questions correctly.");

        if(bonus){
            percentage += BONUS;
            System.out.println("Congratulations! You got a bonus!\n Now your score is "+percentage+"%!");
        }

        if(percentage == 100){ //message if user answered 100% of the questions correctly
            System.out.println("\nNice! You are an expert!");
        } else if (percentage >= 67) { //message if user answered from 67 to 99% of the questions correctly
            System.out.println("\nGood job!!");
        } else if (percentage >= 34) { //message if user answered from 34 to 66% of the questions correctly
            System.out.println("\nI know you could have done better. Try harder next time!");
        } else if (percentage >= 0) { //message if user answered from 0 to 33% of the questions correctly
            System.out.println("\nYou should study in your free time...");
        }
    }
    private static boolean gameOn (boolean game){
        String answer;
        boolean invalid;
        do {
            System.out.println("Do you want to play again? (yes/no)");
            answer = Teclat.llegirString().toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                game = true;
                invalid = false;
            } else if (answer.equals("no") || answer.equals("n")) {
                game = false;
                invalid = false;
            } else {
                System.out.println("Invalid answer, please try again.");
                invalid = true;
            }
        }while(invalid);
        return game;
    }

}