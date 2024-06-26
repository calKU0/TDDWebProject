const getProducts = () => {
    return fetch("/api/products")
        .then(r => r.json())
}

(async () => {
    const products = await getProducts();
    console.log(products);
})();