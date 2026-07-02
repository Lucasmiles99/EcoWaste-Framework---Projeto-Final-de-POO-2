package ecowaste.prototype;

import ecowaste.model.ResiduoEletronico;

public class ResiduoPrototype extends ResiduoEletronico implements PrototipoResiduo {

	public ResiduoPrototype(
	        Long id,
	        String nome,
	        String categoria,
	        double peso,
	        String riscoAmbiental,
	        double valorEstimado,
	        String moeda,
	        String cidade,
	        String estado,
	        String pais,
	        String continente,
	        String destino,
	        String status) {

	    super(
	            id,
	            nome,
	            categoria,
	            peso,
	            riscoAmbiental,
	            valorEstimado,
	            moeda,
	            cidade,
	            estado,
	            pais,
	            continente,
	            destino,
	            status
	    );
	}

	@Override
	public ResiduoPrototype clonar() {
	    return new ResiduoPrototype(
	            getId(),
	            getNome(),
	            getCategoria(),
	            getPeso(),
	            getRiscoAmbiental(),
	            getValorEstimado(),
	            getMoeda(),
	            getCidade(),
	            getEstado(),
	            getPais(),
	            getContinente(),
	            getDestino(),
	            getStatus()
	    );
	}
}