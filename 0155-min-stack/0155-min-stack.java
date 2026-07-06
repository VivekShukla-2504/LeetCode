class MinStack {
  Stack<Integer> st = new Stack<>();
        Stack<Integer> minSt=new Stack<>();
    public MinStack() {
        

    }
    public void push(int value) {
        st.push(value);

        if(minSt.isEmpty() || value<=minSt.peek())
        {
            minSt.push(value);
        }
        else
        {
            minSt.push(minSt.peek());
        }
    }
    
    public void pop() {
        st.pop();
        minSt.pop();
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return minSt.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */