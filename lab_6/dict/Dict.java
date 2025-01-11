package dict;

import java.util.TreeMap;

import dict.exceptions.SectionNotFoundException;
import dict.exceptions.TermNotFoundException;

public class Dict {
    private TreeMap<String, TreeMap<String, String>> data;
    /*
     * {
     * "a": {
     * "apple": "a fruit"
     * "apple": "a fruit"
     * "apple": "a fruit"
     * "apple": "a fruit"
     * "apple": "a fruit"
     * }
     * }
     * 
     */

    private static void populate(Dict d) {
        d.data = new TreeMap<>();
        for (int item = 'a'; item < 'z'; item++) {
            d.data.put(("" + (char) (item)), new TreeMap<>());
        }

        d.data.get("a").put("apple", "A fruit that grows on trees, often red or green in color.");
        d.data.get("b").put("book",
                "A written or printed work consisting of pages glued or sewn together along one side and bound in covers.");
        d.data.get("c").put("cat",
                "A small domesticated carnivorous mammal with soft fur, a short snout, and retractable claws.");
        d.data.get("d").put("dog",
                "A domesticated carnivorous mammal that has a long snout, an acute sense of smell, and a barking, howling, or whining voice.");
        d.data.get("e").put("elephant",
                "A very large mammal with a long trunk, large ears, and two long tusks, found in Africa and Asia.");
        d.data.get("f").put("flower", "The seed-bearing part of a plant, often brightly colored and with petals.");
        d.data.get("g").put("grape",
                "A small, round, green or purple fruit that grows in clusters on a vine and is used to make wine or juice.");
        d.data.get("h").put("house",
                "A building for human habitation, especially one that is a single-family dwelling with a roof and walls standing more or less permanently in one place.");
        d.data.get("i").put("icecream",
                "A frozen dessert made from dairy products, such as milk and cream, and often combined with fruits or other flavors.");
        d.data.get("j").put("jacket",
                "A short coat, typically made of denim or other sturdy fabric, worn as an outer garment.");

        d.data.get("k").put("key", "A small metal instrument used to open or close a lock.");
        d.data.get("l").put("lamp",
                "A device that produces artificial light, typically by the use of an electric power source.");
        d.data.get("m").put("moon", "The natural satellite of the Earth, visible in the sky at night.");
        d.data.get("n").put("notebook", "A book with blank or ruled pages for writing notes.");
        d.data.get("o").put("ocean", "The vast body of salt water that covers much of the Earth's surface.");
        d.data.get("p").put("pen", "A writing instrument with ink that is used to write or draw.");
        d.data.get("q").put("queen", "A female monarch who rules a kingdom or empire.");
        d.data.get("r").put("rainbow",
                "A multicolored arc of light in the sky caused by the refraction and dispersion of the sun's light through raindrops.");
        d.data.get("s").put("sun",
                "The star at the center of the solar system, around which the Earth and other planets orbit.");
        d.data.get("t").put("tree", "A large woody plant with a trunk and branches, typically growing outdoors.");
    }

    public Dict() {
        Dict.populate(this);
    }

    public String define(String word) throws SectionNotFoundException, TermNotFoundException {
        TreeMap<String, String> section = this.data.get(word.substring(0, 1));
        if (section == null) {
            throw new SectionNotFoundException();
        }
        String definition = section.get(word);
        if (definition == null) {
            throw new TermNotFoundException();
        }
        System.out.println(
                definition);
        return "";
    }

}
