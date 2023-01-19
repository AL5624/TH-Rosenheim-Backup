#pragma once
#include "utils.h"
#include <future>
#include <chrono>
#include "set_value_at_thread_exit.h"

void SetValueAtThreadExit::producer_thread(std::promise<int>& promise) {
	utils::sleep(2000);

	int value = 5;

	utils::print("producer_thread: setting value to ", value);

	promise.set_value_at_thread_exit(value);

	utils::sleep(2000);

	utils::print("producer_thread: exit thread");
}

void SetValueAtThreadExit::consumer_thread(std::future<int>& future) {
	utils::print("consumer_thread: waiting for value...");
	utils::time_start("consumer_thread");

	while (true) {
		utils::time_start("consumer_thread_wait_for");
		std::future_status status = future.wait_for(std::chrono::milliseconds(450));
		int waited_for = utils::time_end("consumer_thread_wait_for");

		if (status == std::future_status::ready) {
			utils::print("consumer_thread: shared state is ready; waited for ", waited_for, " milliseconds");
			break;
		}
		else if (status == std::future_status::timeout) {
			utils::print("consumer_thread: shared state is empty; waited for ", waited_for, " milliseconds");
		}
	}

	int value = future.get();

	utils::print("consumer_thread: value received; value = ", value);

	utils::print("consumer_thread: waited for ", utils::time_end("consumer_thread"), " milliseconds");
}

