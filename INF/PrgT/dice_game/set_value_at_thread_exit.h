#pragma once
#include "utils.h"
#include <future>

namespace SetValueAtThreadExit {
	void producer_thread(std::promise<int>& promise);
	void consumer_thread(std::future<int>& future);
}

