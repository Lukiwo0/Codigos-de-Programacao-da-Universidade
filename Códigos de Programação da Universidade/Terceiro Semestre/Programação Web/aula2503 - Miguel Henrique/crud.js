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

// Inicialize o Firebase
const app = firebase.initializeApp(firebaseConfig);
const db = firebase.firestore();
let res = document.getElementById("res");

// Adiciona um listener para o formulário de cadastro
document.getElementById('CalcCustoMaterial').addEventListener('submit', function (event) {
  event.preventDefault();

  const tijolo = document.getElementById('tijolo').value;
  const precotijolo = document.getElementById('precotijolo').value;
  const cimento = document.getElementById('cimento').value;
  const precocimento = document.getElementById('precocimento').value;
  const areia = document.getElementById('areia').value;
  const precoareia = document.getElementById('precoareia').value;

  calcularCusto(tijolo, precotijolo, cimento, precocimento, areia, precoareia);
});

function calcularCusto(tijolo, precotijolo, cimento, precocimento, areia, precoareia) {

  const alerterro = document.getElementById('alertWarning')

  if (tijolo > 0 && cimento > 0 && areia > 0 && precotijolo >= 0.00
    && precocimento >= 0.00 && precoareia >= 0.00) {

    const custototal = (tijolo * precotijolo) + (cimento * precocimento) + (areia * precoareia)
    cadastrarCalc(tijolo, precotijolo, cimento, precocimento, areia, precoareia, custototal);

    alerterro.innerHTML = '';
  }
  else {
    alerterro.innerHTML = `
    <div class="alert alert-warning" role="alert">
      Não adicione letras e valores igual ou menores que 0 ou 0.00.
    </div>`
  }
}

function obterId(callback) {
  db.collection('customaterial').orderBy("id", "desc").limit(1).get()
    .then((querySnapshot) => {
      let proximoId = 1; // Caso não tenha ID ainda
      querySnapshot.forEach((doc) => {
        const ultimoId = doc.data().id;
        proximoId = ultimoId + 1;
      });
      callback(proximoId);
    })
    .catch(error => {
      console.error('Erro ao obter o próximo ID:', error);
      callback(1); // Se der erro vai pro 1 
    });
}

function cadastrarCalc(tijolo, precotijolo, cimento, precocimento, areia, precoareia, custototal) { // Função para cadastrar um Calculo no Firestore

  obterId((proximoId) => {
    db.collection('customaterial').add({
      id: proximoId,
      tijolo: Number(tijolo),
      precotijolo: Number(precotijolo),
      cimento: Number(cimento),
      precocimento: Number(precocimento),
      areia: Number(areia),
      precoareia: Number(precoareia),
      custototal: Number(custototal)
    })
      .then(() => {
        alert('Calculo Cadastrado com Sucesso');
        document.getElementById('CalcCustoMaterial').reset(); // Limpa o formulário
        listarCalcs(); // Atualiza a lista de Calculos após o cadastro
      })
      .catch((error) => {
        console.error('Erro ao cadastrar calculo: ', error);
        alert('Erro ao cadastrar calculo. Tente novamente.');
      });

  })
}

// Função para listar os Calculos cadastrados
function listarCalcs() {
  res.innerHTML = ''; // Limpa a lista antes de atualizar
  db.collection('customaterial').orderBy("id", "asc").get()
    .then((querySnapshot) => {
      querySnapshot.forEach((doc) => {
        const calcs = doc.data(); // Dados do documento
        const itemLista = document.createElement('div');
        itemLista.classList.add("col-md-3", "mb-3")
        itemLista.innerHTML = `
            <div class="card">
                <div class="card-body">
                  <p class="card-text"><strong>ID:</strong> ${calcs.id}</p>
                  <p class="card-text"><strong>Quantidade Tijolo:</strong> ${calcs.tijolo}</p>
                  <p class="card-text"><strong>Preço Tijolo:</strong> R$ ${Number(calcs.precotijolo).toFixed(2)}</p>
                  <p class="card-text"><strong>Quantidade Cimento:</strong> ${calcs.cimento}</p>
                  <p class="card-text"><strong>Preço Cimento:</strong> R$ ${Number(calcs.precocimento).toFixed(2)}</p>
                  <p class="card-text"><strong>Quantidade Areia:</strong> ${calcs.areia}</p>
                  <p class="card-text"><strong>Preço Areia:</strong> R$ ${Number(calcs.precoareia).toFixed(2)}</p>
                  <p class="card-text"><strong>Custo Total:</strong> R$ ${Number(calcs.custototal).toFixed(2)}</p>
                </div>
            </div>  
      `;
        res.appendChild(itemLista); // Adiciona o item à lista
      });
    })
    .catch((error) => {
      console.error('Erro ao buscar usarios: ', error);
    });
}

function limpar() {
  res.innerHTML = ""
  db.collection('customaterial').get().then((querySnapshot) => {
    querySnapshot.forEach((doc) => {
      db.collection('customaterial').doc(doc.id).delete();
    })
  })
}

function removerAnterior() {
  db.collection('customaterial').orderBy("id", "desc").limit(1).get()
    .then((querySnapshot) => {
      if (!querySnapshot.empty) {
        querySnapshot.forEach((doc) => {
          db.collection('customaterial').doc(doc.id).delete().then(() => {
            listarCalcs();
          })
        })
      }
    })
}


// Carrega a lista de Calculos ao carregar a página
window.onload = listarCalcs