#pragma once
#include "utils.h"
#include <future>
#include "set_value.h"

// set_value_at_thread_exit

void SetValue::producer_thread(std::promise<int>& promise) {
	utils::sleep(1000);

	int value = 5;

	utils::print("producer_thread: setting value to ", value);

	promise.set_value_at_thread_exit(value);

	utils::sleep(1000);

	utils::print("producer_thread: exit thread");
}

void SetValue::consumer_thread(std::future<int>& future) {
	utils::print("consumer_thread: waiting for value...");
	utils::time_start("consumer_thread");

	int value = future.get();

	utils::print("consumer_thread: value received; value = ", value);

	utils::print("consumer_thread: waited for ", utils::time_end("consumer_thread"), " milliseconds");
}

