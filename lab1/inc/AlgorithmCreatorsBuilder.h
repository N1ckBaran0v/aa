#ifndef __ALGORITHM_CREATORS_BUILDER__
#define __ALGORITHM_CREATORS_BUILDER__

#include <vector>

#include "AlgorithmCreator.h"

class AlgorithmCreatorsBuilder final {
public:
    AlgorithmCreatorsBuilder() = delete;

    static std::vector<std::pair<std::wstring, std::shared_ptr<AlgorithmCreator>>> getCreators();
    static void setExcludeRecursive(bool);
    static bool getExcludeRecursive();
};

#endif // __ALGORITHM_CREATORS_BUILDER__