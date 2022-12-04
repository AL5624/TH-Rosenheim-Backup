// promise example
#include <iostream>       // std::cout
#include <functional>     // std::ref
#include <thread>         // std::thread
#include <future>         // std::promise, std::future
#include "consumer.h"
#include "producer.h"
#include "combination.h"
#include "utils.h"
#include "equals_overload.h"


void print_int(std::future<int>& fut) {
    int x = fut.get();
    std::cout << "value: " << x << '\n';
}

int main() {
    // utils::print("Consumer Example:\n");
    // run_consumer();
    // utils::print("\n\nProducer Example:\n");
    // run_producer();
    // utils::print("\n\nCombination Example:\n");
    run_combination();
    return 0;
}