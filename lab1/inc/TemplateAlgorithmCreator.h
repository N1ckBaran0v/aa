#ifndef __TEMPLATE_ALGORITHM_CREATOR__
#define __TEMPLATE_ALGORITHM_CREATOR__

#include "AlgorithmCreator.h"
#include "Implementation.h"

template <Implementation<Algorithm> T>
class TemplateAlgorithmCreator : public AlgorithmCreator {
public:
    std::shared_ptr<Algorithm> create(const std::wstring& first, const std::wstring& second) {
        return std::make_shared<T>(first, second);
    }
};

#endif // __TEMPLATE_ALGORITHM_CREATOR__