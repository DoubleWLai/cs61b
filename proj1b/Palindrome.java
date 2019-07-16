public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);

    }

    private boolean isPalindromeHelper(Deque d) {
        if (d.size() <= 1) {
            return true;
        } else if(d.size() % 2 != 0) {
            return false;
        }else if(d.removeFirst() != d.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(d);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque d, CharacterComparator cc) {
        if (d.size() <= 1) {
            return true;
        } else if(!cc.equalChars((char)d.removeFirst(), (char)d.removeLast())) {
            return false;
        } else {
            return isPalindromeHelper(d, cc);
        }
    }












}