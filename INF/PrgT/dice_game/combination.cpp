#include <iostream>       // std::cout
#include <functional>     // std::ref
#include <thread>         // std::thread
#include <future>         // std::promise, std::future#include "combination.h"
#include "consumer.h"
#include "producer.h"
#include "utils.h"

void run_combination() {
    utils::print("Main: creating promise");
    // create promise
    std::promise<int> prom;

    utils::print("Main: getting future from promise");
    // engagement with future
    std::future<int> fut = prom.get_future();

    utils::print("Main: creating consumer and producer thread.");
    std::thread th_producer(producer, std::ref(prom));
    std::thread th_consumer(consumer, std::ref(fut));

    utils::print("Main: Other unrelated busy work.");
    utils::busy_work(1200);

    utils::print("Main: Waiting for threads to end.");
    th_producer.join();
    th_consumer.join();

    utils::print("Main: Finished");
}