// promise example producer
#include <iostream>       // std::cout
#include <functional>     // std::ref
#include <thread>         // std::thread
#include <future>         // std::promise, std::future
#include "utils.h"
#include "producer.h"

void producer(std::promise<int>& prom) {
    utils::print("Producer: calc value");
    int value = utils::complex_calculation();

    utils::print("Producer: setting value to ", value);
    prom.set_value(value);

    utils::print("Producer: free some memory.");
    utils::free_some_memory();

    utils::print("Producer: End of Producer Thread.");
}

void run_producer() {
    utils::print("Main: creating promise");
    // create promise
    std::promise<int> prom;

    utils::print("Main: getting future from promise");
    // engagement with future
    std::future<int> fut = prom.get_future();

    utils::print("Main: creating producer thread...");
    // send promise to new producer thread to do heavy lifting
    std::thread th_producer(producer, std::ref(prom));

    utils::print("Main: doing some initialization");
    // main thread acts as the consumer
    utils::some_initialization(500);

    utils::print("Main: Waiting for Value.");
    int value = fut.get();

    utils::print("Main: Got Value: ", value);

    utils::print("Main: doing some busy work with value");
    // busy work with value
    utils::busy_work(500);

    utils::print("Main: waiting for producer thread");
    th_producer.join();

    utils::print("Main: all finished");
}