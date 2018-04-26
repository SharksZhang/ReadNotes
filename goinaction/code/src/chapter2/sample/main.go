package main

import (
	_ "github.com/sharkslove/goinaction/chapter2/sample/matchers"
	"github.com/sharkslove/goinaction/chapter2/sample/search"
	"log"
	"os"
)

func init() {
	log.SetOutput(os.Stdout)
}

func main() {
	search.Run("American")
}
