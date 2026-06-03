class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();

        //seperate negative and positive numbers
        for(int num:nums){
            if(num<0){
                neg.add(num);
            }
            else{
                pos.add(num);
            }
        }

        //Case 1: No negative numbers
        if(neg.size()==0){
            for(int i=0; i<pos.size();i++){
                pos.set(i,pos.get(i)*pos.get(i));
            }
            return pos.stream().mapToInt(Integer::intValue).toArray();
        }

        //Case 2: No positive numbers
        if(pos.size()==0){
            for(int i=0; i<neg.size();i++){
                neg.set(i,neg.get(i)*neg.get(i));
            }
            Collections.reverse(neg);
            return neg.stream().mapToInt(Integer::intValue).toArray();
        }

        //Case 3: Both negative positive exists
        int i=0,j=0,id=0;
        int n1 = neg.size();
        int n2 = pos.size();
        int res[] = new int[n1+n2];


        //square negatives and reverse them
        for(i=0; i<n1;i++){
            neg.set(i,neg.get(i)*neg.get(i));
        }
        Collections.reverse(neg);

        //square positives
        for(i=0; i<pos.size();i++){
            pos.set(i,pos.get(i)*pos.get(i));
        }

        //merge both sorted arrays
        i=0;
        j=0;

        while(i<n1 && j<n2){
            if(neg.get(i)<=pos.get(j)){
                res[id++]=neg.get(i++);
            }
            else{
                res[id++]=pos.get(j++);
            }
        }

        while(i<n1){
            res[id++]=neg.get(i++);
        }

        while(j<n2){
            res[id++]=pos.get(j++);
        }

        return res;
    }
}