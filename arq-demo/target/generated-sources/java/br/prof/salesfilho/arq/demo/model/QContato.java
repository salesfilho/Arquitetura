package br.prof.salesfilho.arq.demo.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QContato is a Querydsl query type for Contato
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContato extends EntityPathBase<Contato> {

    private static final long serialVersionUID = -10907717L;

    public static final QContato contato = new QContato("contato");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public QContato(String variable) {
        super(Contato.class, forVariable(variable));
    }

    public QContato(Path<? extends Contato> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContato(PathMetadata<?> metadata) {
        super(Contato.class, metadata);
    }

}

