#ifndef __ALGORITHM_CREATOR_H__
#define __ALGORITHM_CREATOR_H__

#include <memory>

#include "Algorithm.h"

class AlgorithmCreator {
public:
    virtual ~AlgorithmCreator() = default;

    virtual std::shared_ptr<Algorithm> create(const std::wstring&, const std::wstring&) = 0;
};

#endif // __ALGORITHM_CREATOR_H__