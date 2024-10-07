#include "AlgorithmCreatorsBuilder.h"

#include "StandartAlgorithm.h"
#include "StandartOptimisedAlgorithm.h"
#include "TemplateAlgorithmCreator.h"
#include "VinogradAlgorithm.h"
#include "VinogradOptimisedAlgorithm.h"

std::vector<std::pair<std::wstring, std::shared_ptr<AlgorithmCreator> > > AlgorithmCreatorsBuilder::getCreators() {
    auto result = std::vector<std::pair<std::wstring, std::shared_ptr<AlgorithmCreator> > >();
    result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator> >(
        L"Стандартный алгоритм", std::make_shared<TemplateAlgorithmCreator<StandartAlgorithm> >()));
    result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator> >(
        L"Стандартный алгоритм с оптимизациями",
        std::make_shared<TemplateAlgorithmCreator<StandartOptimisedAlgorithm> >()));
    result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator> >(
        L"Алгоритм Винограда", std::make_shared<TemplateAlgorithmCreator<VinogradAlgorithm> >()));
    result.push_back(std::make_pair<std::wstring, std::shared_ptr<AlgorithmCreator> >(
        L"Алгоритм Винограда с оптимизациями",
        std::make_shared<TemplateAlgorithmCreator<VinogradOptimisedAlgorithm> >()));
    return result;
}
