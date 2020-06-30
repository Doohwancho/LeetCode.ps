package juneChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchII {
	
	//<Trial01>
	
	//brute-force
	
	//dfs문제여서 dfs를 쓰긴 했지만, visited를 매 iterate마다 deepcopy해야 한다는 점에서 좀 비효율 적인 것 같다.
	
	//이걸 예전에 푼 문제중에 어떤 똑똑이가 쥰내 효율적이게 deepcopy를 핸들링한 케이스가 있긴 했었는데 뭐였더라?
	
    //애초에 int[26]에 board에 있는 알파벳 빈도수 박아 넣은 다음에 String에 있는 알파벳 하나하나의 빈도수가 wordcount랑 맞아떨어질때만 돌려야 하나?
    
    //그것도 조금 비효율적인거같긴 한데;
	
	private boolean[][] deepCopy(boolean[][] original) {
        if (original == null) {
            return null;
        }

        boolean[][] result = new boolean[original.length][original[0].length];
        
        for (int i = 0; i < original.length; i++) {
            for(int j = 0; j < original[0].length; j++){
                result[i][j] = original[i][j];    
            }
        }
        return result;
    }
    
	private boolean dfs(char[][] board, boolean[][] visited, String word, int i, int j, int idx){
        if(idx == word.length()) return false;
        else if(idx == word.length()-1){
            return true;
        }
        
        visited[i][j] = true;    
        boolean flag = false;
        
        if(i < board.length-1 && !visited[i+1][j] && board[i+1][j] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i+1, j, idx+1);
        if(j < board[0].length-1 && !visited[i][j+1] && board[i][j+1] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i, j+1, idx+1);
        if(i > 0 && !visited[i-1][j] && board[i-1][j] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i-1, j, idx+1);
        if(j > 0 && !visited[i][j-1] && board[i][j-1] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i, j-1, idx+1);
        
        return flag;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
    	if(board == null) return new ArrayList<>();
    	
        int m = board.length;
        int n = board[0].length;
        
        List<String> rst = new ArrayList<>();
        Map<Character, List<String>> map = new HashMap<>();
        
        for(int i = 0; i < words.length; i++){ 
            char key = words[i].charAt(0);
            if(map.containsKey(key)){
                List<String> tmp = map.get(key);
                tmp.add(words[i]);
                map.put(key, tmp);
            } else {
                List<String> tmp = new ArrayList<>();
                tmp.add(words[i]);
                map.put(key, tmp);    
            }
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(map.containsKey(board[i][j])){
                    List<String> list = map.get(board[i][j]);
                    
                    for(int p = 0; p < list.size(); p++){
                        String str = list.remove(0);
                        if(dfs(board, new boolean[m][n], str, i, j, 0)){
                            rst.add(str);
                            list.remove(str);
                        } else {
                            list.add(str);
                        }        
                    }
                }
            }
        }
        return rst;
    }
    
    
    //<Trial02>
    
    //TLE뜰때 input이 막 "aaaaaaaaaaaaaaaaaaaaaaaab"이딴거 주길래
    
    //단어 빈도수 체크 먼저 해서 빈도수가 board에 나오는 알파벳 빈도수만큼 나오는애들만 validStrings로 따로 빼서 넣었는데도 안돼네
    
    
	private boolean[][] deepCopy(boolean[][] original) {
        if (original == null) {
            return null;
        }

        boolean[][] result = new boolean[original.length][original[0].length];
        
        for (int i = 0; i < original.length; i++) {
            for(int j = 0; j < original[0].length; j++){
                result[i][j] = original[i][j];    
            }
        }
        return result;
    }
    
	private boolean dfs(char[][] board, boolean[][] visited, String word, int i, int j, int idx){
        if(idx == word.length()) return false;
        else if(idx == word.length()-1){
            return true;
        }
        
        visited[i][j] = true;    
        boolean flag = false;
        
        if(i < board.length-1 && !visited[i+1][j] && board[i+1][j] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i+1, j, idx+1);
        if(j < board[0].length-1 && !visited[i][j+1] && board[i][j+1] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i, j+1, idx+1);
        if(i > 0 && !visited[i-1][j] && board[i-1][j] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i-1, j, idx+1);
        if(j > 0 && !visited[i][j-1] && board[i][j-1] == word.charAt(idx+1)) flag |= dfs(board, deepCopy(visited), word, i, j-1, idx+1);
        
        return flag;
    }
    
    private int[] wordCount(String word){
        int[] a = new int[26];
        for(char c : word.toCharArray()){
            a[c-97]++;
        }
        return a;
    }
    
    private boolean valid(int[] wc, Map<Integer, Integer> alphabet){
        for(int i = 0; i < 26; i++){
            if(wc[i] > 0 && alphabet.getOrDefault(i, 0) < wc[i]){
                return false;
            }
        }
        return true;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
    	if(board == null) return new ArrayList<>();
    	
        int m = board.length;
        int n = board[0].length;
        
        List<String> rst = new ArrayList<>();
        Map<Integer, Integer> alphabet = new HashMap<>();
        
        
        for(int i = 0; i < m; i++){ 
            for(int j = 0; j < n; j++){
                alphabet.put(board[i][j]-97, alphabet.getOrDefault(board[i][j]-97, 0)+1);
            }
        }
        
        Map<Character, List<String>> validStrings = new HashMap<>();
        
        for(String word : words){
            if(valid(wordCount(word), alphabet)){
                if(validStrings.containsKey(word.charAt(0))){
                    List<String> tmp = validStrings.get(word.charAt(0));
                    tmp.add(word);
                    validStrings.put(word.charAt(0), tmp);
                } else {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(word);
                    validStrings.put(word.charAt(0), tmp);
                }
                
            }
        }
        
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(validStrings.containsKey(board[i][j])){
                    
                    List<String> list = validStrings.get(board[i][j]);
                    
                    for(int p = 0; p < list.size(); p++){
                        String str = list.remove(0);
                        if(dfs(board, new boolean[m][n], str, i, j, 0)){
                            rst.add(str);
                            list.remove(str);
                        } else {
                            list.add(str);
                        }        
                    }
                }
            }
        }
        return rst;
    }
    
    //<문제풀이1 by yavinci> 
    
    //꼭 이런 지랄맞은 문제들은 보면 hard더라
    
    //성능 쥰내 잘나오네
    
    //"aaaaaaaaaaaaaaaab"랑 "aaaaaaaaaaaaaaaac"랑 tree로 같은곳에 박아버림. 맨 밑에만 양갈래 b,c로 갈리게 하고 위에 a는 공유함.
    
    //그리고 p.next[c - 'a'] == null 로 a의 끝까지 간다음에 b면 b가 있는지만 확인함. b를 제외한 나머지 알파벳이 있건 없건.
    
    //이것 때문에 TLE가 안뜸. 머리 쥰나 좋네
    
    //그리고 dfs는 특성상 간곳은 갔다고 체크해줘야 되는데, 그러면 boolean visited = new boolean[board.length][board[0].length]를
    
    //매 iterate마다 [i][j]지점에 여기 이미 왔다고 true박고 카피해서 줘야되는데,
    
    //shallow copy해버리면 가다가 막혀서 다른방향갈때 영향을 미치니까 deepcopy해야함.
    
    //근데 매 iterate마다 deepcopy를 해버리면 시간 쥰내걸림 
    
    //근데 얘가 쓴 방법은 매 iterate마다 board에 shallow copy로 일단 왔다 간 곳은 '#'박고 상하좌우dfs끝나는 시점에 다시 board[i][j] = c로 원상복귀 해줌으로써
    
    //중간에 가다가 막혀도 다시 원상복귀 시킴. 
    
    //오져따리
    
    //Runtime: 8 ms
    //Memory Usage: 48.4 MB
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res); 
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
           }
           p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
    

}
