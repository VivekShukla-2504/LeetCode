class Solution:

    def minMaxWaitingTime(self, demand: list[int], fuel: list[int]) -> int:
        N = len(demand)
        F0, F1 = fuel[0], fuel[1]
        
        # Calculate total fuel sum for easy state conversion
        total_demand_prefix = [0] * (N + 1)
        for i in range(N):
            total_demand_prefix[i + 1] = total_demand_prefix[i] + demand[i]

        def get_max_cars_and_min_w(max_w_allowed: int):
            """
            Returns (max_cars_served, min_max_waiting_time_achieved)
            if max_w_allowed is None, returns maximum possible served cars.
            """
            # We track reachable states at step i:
            # State: (f0, S, T0, T1)
            # To optimize, store min T1 for a given (f0, S, T0)
            
            # Initial state before Car 0:
            # f0 = F0, S = 0, T0 = 0, T1 = 0
            current_states = {(F0, 0, 0): 0}  # (f0, S, T0) -> min T1
            
            max_served = 0
            
            for i in range(N):
                d = demand[i]
                next_states = {}
                
                if not current_states:
                    break
                
                max_served = i
                
                for (f0, S, T0), T1 in current_states.items():
                    f1 = F1 - (total_demand_prefix[i] - (F0 - f0))
                    
                    # Option 1: Serve Car i on Dispenser 0
                    if f0 >= d:
                        start0 = max(S, T0)
                        w0 = start0 - S
                        if max_w_allowed is None or w0 <= max_w_allowed:
                            n_f0 = f0 - d
                            n_S = start0
                            n_T0 = start0 + d
                            n_T1 = T1
                            
                            key = (n_f0, n_S, n_T0)
                            if key not in next_states or n_T1 < next_states[key]:
                                next_states[key] = n_T1

                    # Option 2: Serve Car i on Dispenser 1
                    if f1 >= d:
                        start1 = max(S, T1)
                        w1 = start1 - S
                        if max_w_allowed is None or w1 <= max_w_allowed:
                            n_f0 = f0
                            n_S = start1
                            n_T0 = T0
                            n_T1 = start1 + d
                            
                            key = (n_f0, n_S, n_T0)
                            if key not in next_states or n_T1 < next_states[key]:
                                next_states[key] = n_T1
                
                current_states = next_states
            
            if current_states:
                max_served = N
                
            return max_served

        # Step 1: Find the maximum number of cars that can be served (without waiting constraint)
        max_cars = get_max_cars_and_min_w(None)
        
        if max_cars == 0:
            return -1

        # Step 2: Binary Search for the minimum possible maximum waiting time
        low, high = 0, sum(demand)
        ans = high
        
        while low <= high:
            mid = (low + high) // 2
            cars = get_max_cars_and_min_w(mid)
            
            if cars == max_cars:
                ans = mid
                high = mid - 1
            else:
                low = mid + 1
                
        return ans