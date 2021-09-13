import java.util.*;
public class Main{

    public static class TrieNode{

        char value;
        boolean isEnd;
        TrieNode[] children;

        public TrieNode(char ch){
            this.value=ch;
            children=new TrieNode[26];
        }

    }

    public static TrieNode root=new TrieNode('*');
    public static void main(String[] args){


        insert(root,"ang");
        insert(root,"angular");
        insert(root,"anglet");
        insert(root,"anger");
        insert(root,"anxious");
        insert(root,"anniversary");
        insert(root,"anxiety");
        insert(root,"anchorage");

        recommendationSystem("anc");

    }

    public static void insert(TrieNode root,String word){

        if(word.length()==0){
            root.isEnd=true;
            return;
        }

        TrieNode node=root.children[word.charAt(0)-'a'];
        if(node==null){

            root.children[word.charAt(0)-'a']=new TrieNode(word.charAt(0));

            TrieNode childNode=root.children[word.charAt(0)-'a'];
            insert(childNode,word.substring(1));

        }

        TrieNode childNode=root.children[word.charAt(0)-'a'];
        insert(childNode,word.substring(1));

    }

    public static boolean search(TrieNode root,String word){

        if(word.length()==0){
            if(root.isEnd) return true;
            return false;
        }

        TrieNode childNode=root.children[word.charAt(0)-'a'];
        if(childNode==null) return false;

        return search(childNode,word.substring(1));

    }

    public static void dfs(TrieNode root,List<String> list,String temp){
        if(root==null){
            return;
        }

        if(root.isEnd){
            list.add(temp+root.value);
        }

        for(int i=0;i<26;i++){
            TrieNode childNode=root.children[i];
            dfs(childNode,list,temp + root.value);

        }

    }

    public static TrieNode findNode(TrieNode root,String word){

        if(root==null){return null;}
        if(word.length()==0){
            return root;
        }

        TrieNode childNode=root.children[word.charAt(0)-'a'];
        return findNode(childNode,word.substring(1));

    }

    public static void recommendationSystem(String word){

        List<String> list=new ArrayList<>();
        TrieNode node=findNode(root,word);

        dfs(node,list,"");


        for(String str:list){
            String newStr=word+str.substring(1);
            System.out.println(newStr);
        }

    }


}