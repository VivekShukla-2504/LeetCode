class Solution {
    public List<String> fizzBuzz(int n) {
        return IntStream.rangeClosed(1,n).mapToObj(i->i%15==0?"FizzBuzz":i%3==0?"Fizz":i%5==0?"Buzz":""+i).toList();
    }
}