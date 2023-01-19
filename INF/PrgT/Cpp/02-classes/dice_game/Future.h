#pragma once
#include "SharedState.h"

template <class T>
class Future {
public:

	SharedState<T> *shared_state;

	Future(SharedState<T> &shared_state) {
		this->shared_state = &shared_state;
	};

	T get() {
		if (this->shared_state->get_exception() != NULL) {
			throw this->shared_state->get_exception();
		}

		return this->shared_state->get_value();
	};
};