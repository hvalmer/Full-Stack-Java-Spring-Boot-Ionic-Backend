package com.braincustom.fullstackproj.services;

import java.util.Date;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.braincustom.fullstackproj.domain.ItemPedido;
import com.braincustom.fullstackproj.domain.PagamentoComBoleto;
import com.braincustom.fullstackproj.domain.Pedido;
import com.braincustom.fullstackproj.domain.enums.EstadoPagamento;
import com.braincustom.fullstackproj.repositories.ItemPedidoRepository;
import com.braincustom.fullstackproj.repositories.PagamentoRepository;
import com.braincustom.fullstackproj.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository reposi;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired //incluindo dependência para pagamentoRepository
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired//incluindo dependência para itemPedidoRepository
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = reposi.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), null));
		}
	
	//método de inserir um pedido
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);//inserindo um novo pedido
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		//ptgo. com boleto
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		obj = reposi.save(obj); //salvando o pedido no BD
		pagamentoRepository.save(obj.getPagamento());//salvando o pagamento no BD
		for(ItemPedido iped : obj.getItens()) {//salvando os itens do pedido no BD
			iped.setDesconto(0.0);
			iped.setPreco(produtoService.find(iped.getProduto().getId()).getPreco());
			iped.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());//salvando o pedido no BD
		return obj;
	}
}
