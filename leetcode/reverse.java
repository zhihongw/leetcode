package Reverse;

public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String s = "hi!";
		System.out.println(sol.reverseWords(s));
	}
	
    public String reverseWords(String s) {
      StringBuffer sb = new StringBuffer(s.trim());
      int length=sb.length();
      helper(sb, 0, length-1);
      //System.out.println(sb);
      int begin=0;
      int end=0;

      while(end<length) {
        while(end<length&&sb.charAt(end)!=' ')
          end++;
        helper(sb, begin, end-1);
        if(end==length)
            break;
        while(end<length&&sb.charAt(end)==' ')
          end++;
        if(end==length)
          break;
        begin=end;
      }
      //System.out.println(sb);
      StringBuffer res = new StringBuffer();
      for (int i=0;i<sb.length();i++) {
    	  while(i<sb.length()&&sb.charAt(i)!=' ') {
    		  res.append(sb.charAt(i));
    		  i++;
    	  }
    	  if(i==sb.length())
    		  break;
    	  else
    	      res.append(' ');
    	  while(i<sb.length()&&sb.charAt(i)==' ') {
    		  i++;
    	  }
    	  i--;
      }
      return res.toString();
    }
    
    void helper(StringBuffer sb, int begin, int end) {

      if(end==begin) {
        return;
      }
      while(begin<end){
        char ch = sb.charAt(begin);
        sb.setCharAt(begin, sb.charAt(end));
        sb.setCharAt(end, ch);
        begin++;
        end--;
      }
      return;
    }
  };

