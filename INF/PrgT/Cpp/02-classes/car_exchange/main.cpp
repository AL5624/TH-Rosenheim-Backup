// promise example
#include <iostream>       // std::cout
#include <functional>     // std::ref
#include <thread>         // std::thread
#include <future>         // std::promise, std::future
#include <tuple>

void busy_work(int milli = 1000) {
    std::chrono::milliseconds timespan(milli); // or whatever
    std::this_thread::sleep_for(timespan);
}

int calc_val() {
    busy_work();
    return 10;
}

// undependend of other code
void async_processing(std::future<int>& fut) {
    busy_work();
    int x = fut.get();
    std::cout << "Writing " << x << " into Database..." << '\n';
    busy_work();
}


void example_async_processing() {
    std::promise<int> prom;                      // create promise

    std::future<int> fut = prom.get_future();    // engagement with future

    std::thread th1(async_processing, std::ref(fut));  // send future to new thread

    std::cout << "Calculating Value" << '\n';

    // calc value
    int val = calc_val();

    prom.set_value(val);             // fulfill promise

    // do other sutff
    busy_work();

    // (synchronizes with getting the future)
    th1.join();
}

void inner_calc(std::promise<int>& prom) {
    int val = calc_val();
    // prom.set_value_at_thread_exit(val);
    prom.set_value(val);
}

void async_calculation(std::promise<int>& prom) {
    inner_calc(prom);
    busy_work();
    std::cout << "Thread end" << '\n';
}

void example_async_calculation() {
    std::promise<int> prom;                      // create promise

    std::future<int> fut = prom.get_future();    // engagement with future

    std::thread th1(async_calculation, std::ref(prom));  // send promise to new thread

    busy_work(50);

    int val = fut.get();

    std::cout << "value: " << val << '\n';

    busy_work();

    // (synchronizes with getting the future)
    th1.join();
}

void example_combination() {
    std::promise<int> prom;                      // create promise

    std::future<int> fut = prom.get_future();    // engagement with future

    std::thread th1(async_calculation, std::ref(prom));  // send promise to new thread
    std::thread th2(async_processing, std::ref(fut)); // send future to new thread

    busy_work();

    th1.join();
    th2.join();
}

void thread_calc(int& value) {
    value = calc_val();
}

void classic_way() {
    int value;

    std::thread th2(thread_calc, value);
    busy_work(50);
    th2.join();

    std::cout << "value: " << value << '\n';
}

int main()
{
    example_async_processing();
    return 0;
}