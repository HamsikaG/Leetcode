class Twitter {
 List<Integer[]> tweetsList;
    HashMap<Integer,List<Integer>> userFollowing;
    /** Initialize your data structure here. */
    public Twitter() {
        userFollowing = new HashMap<Integer,List<Integer>>();
        tweetsList = new ArrayList<Integer[]>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!userFollowing.containsKey(userId))
        {
           userFollowing.put(userId,new ArrayList<Integer>());
           userFollowing.get(userId).add(userId); 
        }      

          tweetsList.add(new Integer[] {tweetId,userId});
          
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList<Integer>();
        int count = 0;
        for(int i=tweetsList.size(); i>0; i--){
           Integer[] f  = tweetsList.get(i-1);
            if(!userFollowing.containsKey(userId))
                return ans;
                
            if(userFollowing.get(userId).contains(f[1]))
              { ans.add(f[0]); count++; }
            if(count>=10) break;
        }   
        return ans;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userFollowing.containsKey(followerId))
        {
         userFollowing.put(followerId,new ArrayList<Integer>());
         userFollowing.get(followerId).add(followerId);            
        }     
       userFollowing.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId==followeeId) return;
                if(userFollowing.containsKey(followerId))
        userFollowing.get(followerId).remove(new Integer(followeeId));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */