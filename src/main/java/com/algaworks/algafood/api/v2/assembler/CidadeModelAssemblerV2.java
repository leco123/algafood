package com.algaworks.algafood.api.v2.assembler;

//
//@Component
//public class CidadeModelAssemblerV2
//        extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private AlgaLinksV2 algaLinks;
//
//    public CidadeModelAssemblerV2() {
//        super(CidadeControllerV2.class, CidadeModelV2.class);
//    }
//
//    @Override
//    public CidadeModelV2 toModel(Cidade cidade) {
//        CidadeModelV2 cidadeModel = createModelWithId(cidade.getId(), cidade);
//
//        modelMapper.map(cidade, cidadeModel);
//
//        cidadeModel.add(algaLinks.linkToCidades("cidades"));
//
//        return cidadeModel;
//    }
//
//    @Override
//    public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
//        return super.toCollectionModel(entities)
//                .add(algaLinks.linkToCidades());
//    }
//
//}