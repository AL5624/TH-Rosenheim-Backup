#pragma once
#include <future> // std::promise, std::future
#include <thread>
#include <iostream>
#include "set_value.h"
#include "set_exception.h"
#include "set_value_at_thread_exit.h"
#include "share.h"

// SetValue
// SetException
// SetValueAtThreadExit

#define Example SetValue

int main() {
	std::promise<int> promise;
	std::future<int> future = promise.get_future();

	std::thread producer(Example::producer_thread, std::ref(promise));
	std::thread consumer(Example::consumer_thread, std::ref(future));

	producer.join();
	consumer.join();

	return 0;
}

/*
int main() {
    std::promise<int> promise;

    std::future<int> future = promise.get_future();

    promise.set_value(5);

    std::shared_future<int> shared_future_1 = future.share();
    std::shared_future<int> shared_future_2 = future.share();

    std::cout << shared_future_1.get() << std::endl; // 5

    // a second call to "get" only possible with a shared_future
    std::cout << shared_future_1.get() << std::endl; // 5

    try {
        future.get(); // a object already read the shared state -> std::future_errc::no_state
    }
    catch (const std::exception& ex) {
        std::cout << ex.what() << std::endl; // no state
    }

    try {
        shared_future_2.get(); // a object already read the shared state -> std::future_errc::no_state
    }
    catch (const std::exception& ex) {
        std::cout << ex.what() << std::endl; // no state
    }

    shared_future_2 = shared_future_1; // copy of shared_futures work
    std::cout << shared_future_2.get() << std::endl; // 5

    promise = std::promise<int>();
    future = promise.get_future();
    shared_future_2 = future.share();

    // shared_future_1 still points to shared state
    std::cout << shared_future_1.get() << std::endl; // 5

    return 0;
}
*/
/*
int main() {
    std::promise<int> promise;
    std::future<int> future = promise.get_future();
    std::shared_future<int> shared_future = future.share();

    std::thread producer(Share::producer_thread, std::ref(promise));
    std::thread consumer1(Share::consumer_thread_1, shared_future);
    std::thread consumer2(Share::consumer_thread_2, shared_future);

    producer.join();
    consumer1.join();
    consumer2.join();

    return 0;
}
*/