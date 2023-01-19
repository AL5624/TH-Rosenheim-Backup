#pragma once
#include "utils.h"
#include <future>
#include "share.h"

void Share::producer_thread(std::promise<int>& promise) {
	utils::sleep(2000);

	int value = 5;

	utils::print("producer_thread: setting value to ", value);

	promise.set_value(value);
}

void Share::consumer_thread_1(std::shared_future<int> shared_future) {
	utils::print("consumer_thread_1: waiting for value...");
	utils::time_start("consumer_thread_1");

	int value = shared_future.get();

	utils::print("consumer_thread_1: value received; value = ", value);

	utils::print("consumer_thread_1: waited for ", utils::time_end("consumer_thread_1"), " milliseconds");
}

void Share::consumer_thread_2(std::shared_future<int> shared_future) {
	utils::print("consumer_threa_2: waiting for value...");
	utils::time_start("consumer_threa_2");

	int value = shared_future.get();

	utils::print("consumer_threa_2: value received; value = ", value);

	utils::print("consumer_threa_2: waited for ", utils::time_end("consumer_threa_2"), " milliseconds");
}

