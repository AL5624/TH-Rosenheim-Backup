// promise example consumer
#include <iostream>       // std::cout
#include <functional>     // std::ref
#include <thread>         // std::thread
#include <future>         // std::promise, std::future
#include "utils.h"
#include "consumer.h"

void consumer(std::future<int>& fut) {
    utils::print("Consumer: some_initialization");
    utils::some_initialization(500);

    utils::print("Consumer: waiting for value");
    // waiting for value
    int x = 0;
    try {
       x = fut.get();
    }
    catch (const std::exception& e) {
        utils::print("Exception");
    }

    utils::print("Consumer: Got Value. Working with Value. Value: ", x);
    // some busy work with value
    utils::busy_work();

    utils::print("Consumer: End of Consumer Thread.");
}

void run_consumer() {
    utils::print("Main: creating promise");
    // create promise
    std::promise<int> prom;

    utils::print("Main: getting future from promise");
    // engagement with future
    std::future<int> fut = prom.get_future();

    utils::print("Main: creating consumer thread...");
    // send future to new consumer thread
    // main does not care when and what happens with value
    std::thread th_consumer(consumer, std::ref(fut));  

    utils::print("Main: starting complex value calculation");
    // main thread acts as the producer
    int value = utils::complex_calculation();

    utils::print("Main: setting value to ", value);
    // fulfill promise
    // as producer main should not do anything else with value
    prom.set_value(value);

    utils::print("Main: doing some busy work");
    // some unrelated work
    utils::busy_work(500);

    utils::print("Main: waiting for consumer thread");
    th_consumer.join();

    utils::print("Main: all finished");
}