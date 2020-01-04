package algorithms.leetcode.l500_480_1_3.l500_490_11_18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

//Given a List of words, return the words that can be typed using letters of
// alphabet on only one row's of American keyboard like the image below.
//
//
//
//
//
//
// Example:
//
//
//Input: ["Hello", "Alaska", "Dad", "Peace"]
//Output: ["Alaska", "Dad"]
//
//
//
//
// Note:
//
//
// You may use one character in the keyboard more than once.
// You may assume the input string will only contain letters of alphabet.
//
// Related Topics Hash Table

/**
 * Tag: HashMap,正则表达式,easy
 *
 * */
public class L500_KeyboardRow {



//    class Solution {
        public static HashMap<Character,Integer> keyboard = new HashMap<Character,Integer>(30);
        public static Character[] firstRow = new Character[]{'q','w','e','r','t','y','u','i','o','p'};
        public static Character[] secondRow = new Character[]{'a','s','d','f','g','h','j','k','l'};
        public static Character[] thirdRow = new Character[]{'z','x','c','v','b','n'};
        static {
            for(Character tmp: firstRow){
                keyboard.put(tmp,1);
            }
            for(Character tmp: secondRow){
                keyboard.put(tmp,2);
            }
            for(Character tmp: thirdRow){
                keyboard.put(tmp,3);
            }

        }
        public String[] findWords(String[] words) {
            if(words == null || words.length == 0) return words;
            ArrayList<String> resultList = new ArrayList<String>();
            for(String word:words){
                boolean flag = true;
                int rowIndex = -1;
                for(Character tmp: word.toLowerCase().toCharArray()){
                    if(rowIndex == -1){
                        rowIndex=keyboard.get(tmp);
                    }else{
                        if(rowIndex != keyboard.get(tmp)){
                            flag=false;
                            break;
                        }
                    }
                }
                if(flag) resultList.add(word);
            }
            String[] result = new String[resultList.size()];
            resultList.toArray(result);
            return result;

        }
//    }


//    public static  HashMap<Character,Integer> keyboats = new HashMap<Character, Integer>();
//
//    static {
//        Character[] firstArray = (Character[]) Arrays.asList('q','w','e','r','t','y','u','i','o','p').toArray();
//        Character[] secondArray = (Character[]) Arrays.asList('a','s','d','f','g','h','j','k','l').toArray();
//        Character[] thirdArray = (Character[]) Arrays.asList('z','x','c','v','b','n','m').toArray();
//        for (Character tmp:firstArray){
//            keyboats.put(tmp,1);
//        }
//        for (Character tmp:secondArray){
//            keyboats.put(tmp,2);
//        }
//        for (Character tmp:thirdArray){
//            keyboats.put(tmp,3);
//        }
//
//    }
//
//    public String[] findWords(String[] words) {
//        List<String> result = new ArrayList<String>();
//        for (String tmp : words){
//            int i = -1;boolean flag = true;
//            for (Character c: tmp.toLowerCase().toCharArray()){
//               int rows= keyboats.get(c);
//               if(i == -1){
//                   i=rows;
//               }else if(i != rows){
//                   flag = false;
//                   break;
//               }
//
//            }
//            if(flag) result.add(tmp);
//        }
//
////        String[] re = new String[result.size()];
////        result.toArray(re);
////        return re;
//        // 语法的胜利
//        return result.toArray(new String[0]);
//    }
//
//
//    public String[] findWords_think(String[] words) {
//        return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
//    }

    public static void main(String[] args){
        String[] para = (String[]) Arrays.asList("abdfs","cccd","a","qwwewm").toArray();
        L500_KeyboardRow test = new L500_KeyboardRow();

        String[] res =  test.findWords(para);
        System.out.println(Arrays.toString(res));
    }
}
