// Configuração do Firebase
const firebaseConfig = {
  apiKey: "",
  authDomain: "",
  projectId: "",
  storageBucket: "",
  messagingSenderId: "",
  appId: "",
  measurementId: ""
};

//Inicializa o Firebase
const app = firebase.initializeApp(firebaseConfig)
const db = firebase.firestore();

// Variáveis de controle
let editando = false;
let itemIDatual = null;

// Evento do botão Cadastrar
document.getElementById('cadastroForm').addEventListener('submit', function (event) {
  event.preventDefault();

  const tipo = document.getElementById('tipo').value;
  const valor = document.getElementById('valor').value
  const data = document.getElementById('data').value
  const descricao = document.getElementById('descricao').value

  if (editando) {
    atualizarCarteira(itemIDatual, tipo, valor, data, descricao)
  } else {
    cadastrarCarteira(tipo, valor, data, descricao)
  }
})

// Evento do botão Cancelar
document.getElementById('btnCancelar').addEventListener('click', cancelarEdicao)

// Função cadastar carteira
function cadastrarCarteira(tipo, valor, data, descricao) {
  db.collection('carteira').add({
    tipo: tipo,
    valor: valor,
    data: data,
    descricao: descricao
  })
    .then(() => {
      alert('Carteira cadastrada com Sucesso!')
      document.getElementById('cadastroForm').reset()
      listarCarteira()
    })
    .catch((error) => {
      console.error('Erro ao cadastrar carteira: ', error)
      alert('Erro ao cadastrar carteira.')
    })
}

// Função para listar carteiras
function listarCarteira() {
  const tbody = document.querySelector('#listaCarteira tbody')
  tbody.innerHTML = '<tr><td colspan="4">Carregando...</td></tr>'

  db.collection('carteira').get()
    .then((querySnapshot) => {
      tbody.innerHTML = '';

      if (querySnapshot.empty) {
        tbody.innerHTML = '<tr><td colspan="4">Nenhuma carteira cadastrada</td></tr>'
        return
      }

      querySnapshot.forEach((doc) => {
        const carteira = doc.data()
        const iditem = doc.id

        const row = document.createElement('tr')
        row.innerHTML = `
            <td>${carteira.tipo}</td>
            <td>R$ ${carteira.valor}</td>
            <td>${carteira.data}</td>
            <td>${carteira.descricao}</td>
            <td class="actions">
              <button onclick="editarCarteira('${iditem}', '${carteira.tipo}', '${carteira.valor}', '${carteira.data}', '${carteira.descricao}')">Editar</button>
              <button onclick="excluirCarteira('${iditem}')">Excluir</button>
            </td>
        `

        tbody.appendChild(row)
      })
    })
    .catch((error) => {
      console.error('Erro ao carregar carteiras: ', error)
      tbody.innerHTML = '<tr><td colspan="4">Erro ao carregar carteiras</td></tr>'
    })
}

// Função para editar a carteira
function editarCarteira(id, tipo, valor, data, descricao) {
  editando = true
  itemIDatual = id

  document.getElementById('itemID').value = id
  document.getElementById('tipo').value = tipo
  document.getElementById('valor').value = valor
  document.getElementById('data').value = data
  document.getElementById('descricao').value = descricao

  document.getElementById('btnCadastrar').textContent = 'Atualizar'
  document.getElementById('btnCancelar').style.display = 'inline-block'
}

// Função para atualizar carteira
function atualizarCarteira(id, tipo, valor, data, descricao) {
  db.collection('carteira').doc(id).update({
    tipo: tipo,
    valor: valor,
    data: data,
    descricao: descricao
  })
  .then(() => {
    alert('Carteira atualizada com sucesso!')
    cancelarEdicao()
    listarCarteira()
  })
  .catch((error) => {
    console.error('Erro ao atualizar carteira: ', error)
    alert('Erro ao atualizar carteira')
  })
}

// Função para excluir carteira
function excluirCarteira(id) {
  if (confirm('Tem certeza que deseja excluir esta Carteira?')) {
    db.collection('carteira').doc(id).delete()
      .then(() => {
        alert('Carteira excluída com sucesso!')
        listarCarteira()
      })
      .catch((error) => {
        console.error('Erro ao excluir carteira: ', error)
        alert('Erro ao excluir carteira')
      })
  }
}

// Função para cancelar carteira
function cancelarEdicao() {
  editando = false
  alunoIdAtual = null

  document.getElementById('cadastroForm').reset()
  document.getElementById('itemID').value = ''

  document.getElementById('btnCadastrar').textContent = 'Cadastrar'
  document.getElementById('btnCancelar').style.display = 'none'
}

// Carrega as carteiras quando a página é carregada
window.onload = listarCarteira