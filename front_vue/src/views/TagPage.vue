<template>
  <div>
    {{ this.$route.params.tagname }}

    <div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>

      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
      <div class="column">
        <div class="card">
          <tr>
            <td rowspan="2" style="width:120px">
              <center><h1>#</h1></center>
            </td>
            <td style="font-size:50px; align-content:center;">
              123123
            </td>
          </tr>
          <tr>
            <td>게시물 123123</td>
          </tr>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Float four columns side by side */
.column {
  float: left;
  width: 45%;
  padding: 0 10px 10px 10px;
}

/* Remove extra left and right margins, due to padding */
.row {
  margin: 0 -5px;
}

/* Clear floats after the columns */
.row:after {
  content: '';
  display: table;
  clear: both;
}

/* Responsive columns */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}

/* Style the counter cards */
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  background-color: #f1f1f1;
}
</style>
